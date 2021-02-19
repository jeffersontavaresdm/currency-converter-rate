package com.rate.entity.dto

import java.time.LocalDate

data class CoinDTO(

  val coinType: String,
  val name: String,
  val valueToday: Double,
  val lastUpdate: LocalDate

)
