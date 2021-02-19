package com.rate.controller

import com.rate.entity.Coin
import com.rate.entity.dto.CoinDTO
import com.rate.service.CoinRateService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@Suppress("unused")
@RestController
@RequestMapping("/api/v1/rate")
class CoinRateController(val coinRateService: CoinRateService) {

  @GetMapping("/{coinType}")
  fun getCoinInfo(@PathVariable("coinType") coinType: String): List<CoinDTO> {

    val coinList = coinRateService.rate(coinType.toUpperCase())

    return coinList.map { coin ->
      CoinDTO(
        coinType = coin.type,
        name = coin.name,
        valueToday = coin.value,
        lastUpdate = coin.lastUpdateTime
      )
    }
  }


  @GetMapping
  fun list(

    @RequestParam("page") page: Int,
    @RequestParam("pageSize") pageSize: Int

  ): Mono<Page<Coin>> {

  }
}