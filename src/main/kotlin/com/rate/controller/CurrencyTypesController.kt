package com.rate.controller

import com.rate.entity.dto.CurrencyTypesData
import com.rate.service.CurrencyTypesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class CurrencyTypesController(private val currentTypesService: CurrencyTypesService) {

  @GetMapping
  fun list(): CurrencyTypesData {
    return currentTypesService.getTypes()
  }
}