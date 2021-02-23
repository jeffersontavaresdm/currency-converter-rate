package com.rate.entity.dto

import java.time.LocalDate

data class CoinDTO(

  val coinType: String,
  val name: String,
  val maxValue: Double,
  val minValue: Double,
  val date: LocalDate

)
