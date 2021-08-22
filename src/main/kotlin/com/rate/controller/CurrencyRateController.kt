package com.rate.controller

import com.rate.entity.dto.CurrencyDTO
import com.rate.service.CurrencyRateService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/api/v1/rate")
class CurrencyRateController(val currencyRateService: CurrencyRateService) {

  @GetMapping("/{currencyType}")
  fun list(
    @PathVariable("currencyType") currencyType: String,
    pageable: Pageable,
  ): Page<CurrencyDTO> {

    val infoList = currencyRateService.getInfoList(
      currencyType = currencyType.toUpperCase(),
      pageable = pageable
    )

    infoList.map { currency ->
      CurrencyDTO(
        currencyType = currency.type,
        name = currency.name,
        maxValue = currency.maxValue,
        minValue = currency.minValue,
        date = currency.savedDate,
      )
    }
  }
}
