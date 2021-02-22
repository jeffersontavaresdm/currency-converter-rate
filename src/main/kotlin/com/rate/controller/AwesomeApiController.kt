package com.rate.controller

import com.rate.api.Asset
import com.rate.service.AwesomeApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/coinType")
class AwesomeApiController(val service: AwesomeApiService) {

  @GetMapping("/all")
  fun getAllCoin(): Map<String, Asset> {
    return service.getAllCoins()
  }

  @GetMapping("/{coin}")
  fun getCoinInfo(

    @PathVariable("coin") coin: String

  ): Map<String, Asset> {

    return service.getCoin(coin)
  }
}