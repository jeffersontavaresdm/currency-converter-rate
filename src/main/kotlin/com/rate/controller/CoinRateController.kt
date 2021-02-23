package com.rate.controller

import com.rate.entity.dto.CoinDTO
import com.rate.service.CoinRateService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@Suppress("unused")
@RestController
@RequestMapping("/api/v1/rate")
class CoinRateController(val coinRateService: CoinRateService) {

  @GetMapping("/{coinType}")
  fun getCoinInfo(

    @PathVariable("coinType") coinType: String,
    page: Pageable

  ): Page<CoinDTO> {

    val infoList = coinRateService.rate(coinType.toUpperCase(), page)

    return infoList.map { coin ->
      CoinDTO(
        coinType = coin.type,
        name = coin.name,
        maxValue = coin.maxValue,
        minValue = coin.minValue,
        date = coin.lastUpdateDate
      )
    }
  }

  @GetMapping("/all")
  fun getAllToday(

    @RequestParam("page") page: Int,
    @RequestParam("pageSize") pageSize: Int

  ): Page<CoinDTO> {

    val values = coinRateService.getAllToday(PageRequest.of(page, pageSize))

    return values.map { coin ->
      CoinDTO(
        coinType = coin.type,
        name = coin.name,
        maxValue = coin.maxValue,
        minValue = coin.minValue,
        date = coin.lastUpdateDate
      )
    }
  }
}