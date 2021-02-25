@file:Suppress("unused")

package com.rate.jobs

import com.rate.api.AwesomeApi
import com.rate.entity.Coin
import com.rate.exception.BadRequestException
import com.rate.repository.CoinRepository
import com.rate.service.CoinRateService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class CoinJob(

  private val coinRateService: CoinRateService,
  private val coinRepository: CoinRepository,
  private val awesomeApi: AwesomeApi,

  ) {

  private val logger = LoggerFactory.getLogger(javaClass)

  @Scheduled(fixedDelay = 1000)
  fun run() {

    logger.info("updating values...")

    val response = awesomeApi.makeApiCall("/all").execute()

    if (response.isSuccessful) {

      val responseBody = response.body()
        ?: throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())
      val assets = responseBody.values

      assets.forEach { asset ->

        val now = OffsetDateTime.now()
        val coinInfoToday =
          coinRepository.findByTypeAndSavedDate(asset.code, now.toLocalDate())
            ?: coinRateService
              .update(
                Coin(
                  id = null,
                  type = asset.code,
                  name = asset.name,
                  maxValue = asset.high,
                  minValue = asset.low,
                )
              )
        coinRateService.update(
          coinInfoToday.copy(
            maxValue = asset.high,
            minValue = asset.low,
            lastUpdateTime = now.toLocalTime(),
            savedDate = now.toLocalDate()
          )
        )
      }
      logger.info("...updates successful!\n")
    } else {
      logger.info("some error was found")
      logger.info(response.errorBody().toString())
    }
  }
}