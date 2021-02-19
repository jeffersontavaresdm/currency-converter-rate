package com.rate.controller

import com.rate.api.Asset
import com.rate.service.AwesomeApiTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/test")
class AwesomeApiTestController(val testService: AwesomeApiTestService) {

  @GetMapping("/all")
  fun getAllCoin(): Map<String, Asset> {
    return testService.getAllCoins()
  }

  @GetMapping("/{coin}")
  fun getCoinInfo(

    @PathVariable("coin") coin: String

  ): Map<String, Asset> {

    return testService.getCoin(coin)
  }
}