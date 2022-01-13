package com.rate.service

import com.rate.entity.dto.CurrencyTypesData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class CurrencyTypesController(private val currentTypesService: CurrentTypesService) {

  @GetMapping
  fun list(): CurrencyTypesData {
    return currentTypesService.getTypes()
  }

  @GetMapping("/api/v1/rate")
  fun getTypes(): CurrencyTypesData {
    return currentTypesService.getTypes()
  }
}