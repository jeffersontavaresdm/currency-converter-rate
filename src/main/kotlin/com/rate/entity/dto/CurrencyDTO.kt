package com.rate.entity.dto

import java.time.LocalDate

data class CurrencyDTO(
  val currencyType: String,
  val name: String,
  val maxValue: Double,
  val minValue: Double,
  val date: LocalDate,
)