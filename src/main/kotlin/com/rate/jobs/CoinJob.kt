package com.rate.jobs

import com.rate.repository.CoinRepository
import com.rate.service.CoinRateService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CoinJob(

  private val coinRepository: CoinRepository,
  private val coinRateService: CoinRateService

) {

  private val logger = LoggerFactory.getLogger(javaClass)

  fun run() {

  }
}