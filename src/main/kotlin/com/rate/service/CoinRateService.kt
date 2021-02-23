package com.rate.service

import com.rate.api.AwesomeApi
import com.rate.entity.Coin
import com.rate.exception.BadRequestException
import com.rate.exception.ValidationException
import com.rate.repository.CoinRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.OffsetDateTime

@Service
class CoinRateService(

  val coinRepository: CoinRepository,
  val awesomeApi: AwesomeApi

) {

  fun rate(coin: String, page: Pageable): Page<Coin> {

    val response = awesomeApi.makeApiCall(coin).execute()
    if (response.isSuccessful) {

      val responseBody = response.body() ?: throw ValidationException()
      val assets = responseBody.values

      assets.forEach { asset ->

        val today = LocalDate.now()
        val coinInfoToday = coinRepository
          .findFirstByTypeAndLastUpdateDateOrderByLastUpdateDateDesc(coin, today) ?: coinRepository
          .save(
            Coin(
              id = null,
              type = asset.code,
              name = asset.name,
              maxValue = asset.high,
              minValue = asset.low,
              lastUpdateDate = today
            )
          )
        coinRepository.save(
          Coin(
            id = coinInfoToday.id,
            type = asset.code,
            name = asset.name,
            maxValue = asset.high,
            minValue = asset.low,
            lastUpdateDate = today
          )
        )
      }
    } else throw BadRequestException("currency abbreviation is not correct")
    return coinRepository.findAllByTypeOrderByLastUpdateDateDesc(coin, page)
  }

  fun getAllToday(pageRequest: PageRequest): Page<Coin> {

    val responseBody = awesomeApi
      .makeApiCall("")
      .execute()
      .body() ?: throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())

    responseBody.values
      .forEach { asset ->

        val coin = coinRepository
          .findFirstByTypeAndLastUpdateDateOrderByLastUpdateDateDesc(asset.code, LocalDate.now())
          ?: coinRepository.save(
            Coin(
              type = asset.code,
              name = asset.name,
              maxValue = asset.high,
              minValue = asset.low,
              lastUpdateDate = OffsetDateTime.now().toLocalDate()
            )
          )
      }
    return coinRepository.findAll(pageRequest)
  }
}