package com.rate.service

import com.rate.api.AwesomeApi
import com.rate.entity.Coin
import com.rate.exception.BadRequestException
import com.rate.exception.NotFoundException
import com.rate.exception.ValidationException
import com.rate.repository.CoinRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CoinRateService(

  val coinRepository: CoinRepository,
  val awesomeApi: AwesomeApi

) {

  fun rate(coin: String): List<Coin> {

    if (coin.isNotBlank() && coin.length == 3) {

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
                value = asset.high,
                lastUpdateDate = today
              )
            )
          coinRepository.save(
            Coin(
              id = coinInfoToday.id,
              type = asset.code,
              name = asset.name,
              value = asset.high,
              lastUpdateDate = today
            )
          )
        }
      } else throw ValidationException("this currency does not exist")
    } else throw BadRequestException("currency abbreviation is not correct")
    return coinRepository.findAllByTypeOrderByLastUpdateDateDesc(coin)
  }

  fun getAllToday(): List<Coin> {

    val response = awesomeApi
      .makeApiCall("")
      .execute()

    if (response.isSuccessful) {
      val responseBody = response.body() ?: throw NotFoundException()

      return responseBody
        .values
        .map { asset ->
          Coin(
            type = asset.code,
            name = asset.name,
            value = asset.high,
            lastUpdateDate = LocalDate.now()
          )
        }
    } else throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())
  }
}