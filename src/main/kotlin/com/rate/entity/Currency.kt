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
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
    other as Currency

    return id != null && id == other.id
  }

  override fun hashCode(): Int = javaClass.hashCode()

  @Override
  override fun toString(): String {
    return """
      ${this::class.simpleName} {
        id: $id
        type: $type
        name: $name
        maxValue: $maxValue
        minValue: $minValue
        lastUpdateTime: $lastUpdateTime
        savedDate: $savedDate
      }
      
      """.trimMargin()
  }
}