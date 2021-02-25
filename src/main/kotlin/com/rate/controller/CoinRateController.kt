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
    @RequestParam("page") page: Int,
    @RequestParam("pageSize") pageSize: Int,
//    pageable: Pageable,

    ): Page<CoinDTO> {

    val infoList = coinRateService.currencyValue(
      coin = coinType.toUpperCase(),
      pageable = PageRequest.of(page, pageSize)
//      pageable = pageable
    )

    return infoList.map { coin ->
      CoinDTO(
        coinType = coin.type,
        name = coin.name,
        maxValue = coin.maxValue,
        minValue = coin.minValue,
        date = coin.savedDate
      )
    }
  }
}