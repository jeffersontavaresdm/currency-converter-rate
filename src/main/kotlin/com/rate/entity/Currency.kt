package com.rate.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Currency(
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
  val lastUpdateTime: LocalTime = LocalTime.now(),

  @field:JsonIgnore
  val savedDate: LocalDate = LocalDate.now(),
)
