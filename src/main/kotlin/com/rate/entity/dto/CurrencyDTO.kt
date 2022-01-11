package com.rate.entity.dto

import com.rate.entity.Currency
import java.time.LocalDate

data class CurrencyDTO(
  val type: String,
  val name: String,
  val maxValue: Double,
  val minValue: Double,
  val date: LocalDate,
)

fun Currency.toDTO(): CurrencyDTO {
  return CurrencyDTO(
    type = type,
    name = name,
    maxValue = maxValue,
    minValue = minValue,
    date = savedDate
  )
}