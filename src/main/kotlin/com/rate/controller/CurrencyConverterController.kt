package com.rate.controller

import com.rate.entity.dto.CurrencyDTO
import com.rate.service.CurrencyConverterService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/api/v1/rate")
class CurrencyConverterController(val service: CurrencyConverterService) {

  @GetMapping("/{type}")
  fun list(
    @PathVariable("type") currencyType: String,
    pageable: Pageable,
  ): Page<CurrencyDTO> {

    return service.getInfoList(currencyType.toUpperCase(), pageable)
  }
}