package com.rate.jobs

import com.rate.api.AwesomeApi
import com.rate.exception.BadRequestException
import com.rate.repository.CoinRepository
import com.rate.service.CoinRateService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CoinJob(

  private val coinRepository: CoinRepository,
  private val coinRateService: CoinRateService,
  private val awesomeApi: AwesomeApi,

  ) {

  private val logger = LoggerFactory.getLogger(javaClass)

  @Scheduled(fixedDelay = 3000)
  fun run() {

    logger.info("updating values...")

    val response = awesomeApi.makeApiCall("").execute()

    if (response.isSuccessful) {

      val responseBody = response.body()
        ?: throw BadRequestException(HttpStatus.EXPECTATION_FAILED.toString())

      responseBody.values.map { asset -> coinRateService.rate(asset.code) }

      logger.info("...updates successful!\n")
    }
  }
}