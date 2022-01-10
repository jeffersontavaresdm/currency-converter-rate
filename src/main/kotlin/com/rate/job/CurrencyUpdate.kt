@file:Suppress("unused")

package com.rate.job

import com.rate.api.AwesomeApi
import com.rate.entity.Currency
import com.rate.exception.BadRequestException
import com.rate.repository.CurrencyRepository
import com.rate.service.CurrencyConverterService
import com.rate.utils.loggerFor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class CurrencyUpdate(
  private val service: CurrencyConverterService,
  private val currencyRepository: CurrencyRepository,
  private val api: AwesomeApi,
) {

  private val logger = loggerFor()

  @Scheduled(fixedDelay = 180000)
  fun run() {

    logger.info("updating values...")

    val response = api
      .makeCall("/all")
      .execute()

    if (response.isSuccessful) {
      val responseBody = response.body() ?: throw BadRequestException("response body is null")
      val assets = responseBody.values

      assets.forEach { asset ->
        val now = OffsetDateTime.now()
        val infoToday = currencyRepository
          .findByTypeAndSavedDate(
            currency = asset.code,
            today = now.toLocalDate()
          ) ?: service.save(
          Currency(
            id = null,
            type = asset.code,
            name = asset.name,
            maxValue = asset.high,
            minValue = asset.low,
          )
        )
        service.save(
          infoToday.copy(
            maxValue = asset.high,
            minValue = asset.low,
            lastUpdateTime = now.toLocalTime(),
            savedDate = now.toLocalDate()
          )
        )
      }
      logger.info("...updates successfully!")
    } else {
      response.errorBody()?.let {
        logger.error("some error was found. Error: {}", response.errorBody()!!.string())
      }
    }
  }
}