package com.rate.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.rate.entity.dto.CurrencyTypesData
import com.rate.enum.CurrencyTypes
import org.springframework.stereotype.Service

@Service
class CurrentTypesService(private val mapper: ObjectMapper) {

  fun getTypes(): CurrencyTypesData {
    val string = StringBuilder()
      .apply {
        append("{\n")
        append("  all: \"Um de cada valor de cada uma das moedas no dia\"),")
        CurrencyTypes.values().forEach {
          append("  \"")
          append(it.name)
          append("\": \"")
          append(it.value)
          append("\"")
          if (it != CurrencyTypes.values().last()) {
            append(",\n")
          }
        }
        append("\n}")
      }.toString()

    return mapper
      .findAndRegisterModules()
      .readValue(string, CurrencyTypesData::class.java)
  }
}