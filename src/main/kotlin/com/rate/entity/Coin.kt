package com.rate.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Coin(

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @field:JsonIgnore
  val type: String,

  @field:JsonIgnore
  val name: String,

  @field:JsonIgnore
  val maxValue: Double,

  @field:JsonIgnore
  val minValue: Double,

  @field:JsonIgnore
  val lastUpdateTime: LocalTime,

  @field:JsonIgnore
  val savedDate: LocalDate,

  )