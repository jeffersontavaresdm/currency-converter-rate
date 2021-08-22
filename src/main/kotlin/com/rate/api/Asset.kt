package com.rate.api

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Asset(
  val code: String,
  val name: String,
  val high: Double,
  val low: Double,

  @JsonProperty("create_date")
  @JsonFormat(pattern = "Yyyy-MM-dd HH:mm:ss")
  val lastUpdate: Date
)