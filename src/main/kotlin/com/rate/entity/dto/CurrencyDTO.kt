package com.rate.entity.dto

import java.time.LocalDate

data class CurrencyDTO(
  val currencyType: String,
  val name: String,
  val maxValue: Double,
  val minValue: Double,
  val date: LocalDate
) {
  override fun toString(): String {
    return "\nCurrency: {\n  type: $currencyType\n  name: $name\n  maxValue: $maxValue\n  minValue: $minValue\n  date: $date\n}\n"
  }
}