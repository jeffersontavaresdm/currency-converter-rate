package com.rate.service

import com.rate.api.AwesomeApi
import com.rate.entity.Coin
import com.rate.exception.BadRequestException
import com.rate.repository.CoinRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class CoinRateService(

  private val coinRepository: CoinRepository,
  private val awesomeApi: AwesomeApi,

  ) {

  fun rate(coin: String) {

    val response = awesomeApi.makeApiCall(coin).execute()

    if (response.isSuccessful) {
      val responseBody = response.body()
        ?: throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())

      val assets = responseBody.values

      assets.forEach { asset ->

        val now = OffsetDateTime.now()
        val coinInfoToday = coinRepository
          .findByTypeAndSavedDate(asset.code, now.toLocalDate()) ?: coinRepository
          .save(
            Coin(
              id = null,
              type = asset.code,
              name = asset.name,
              maxValue = asset.high,
              minValue = asset.low,
              lastUpdateTime = now.toLocalTime(),
              savedDate = now.toLocalDate()
            )
          )
        coinRepository.save(
          coinInfoToday.copy(
            maxValue = asset.high,
            minValue = asset.low,
            lastUpdateTime = now.toLocalTime()
          )
        )
      }
    } else throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())
  }

  fun currencyValue(coin: String, page: Pageable): Page<Coin> {
    return coinRepository.findAllByTypeOrderBySavedDateDesc(coin, page)
  }
}