package com.rate.utils

import com.rate.enum.CurrencyTypes

fun Array<CurrencyTypes>.convertToJson(): String = StringBuilder()
  .let { builder ->
    builder.append("{\n")
    this.forEach { currency ->
      builder.append("  \"")
      builder.append(currency.name.toUpperCase())
      builder.append("\": \"")
      builder.append(currency.value)
      builder.append("\"")
      if (currency != CurrencyTypes.values().last()) {
        builder.append(",\n")
      }
    }
    builder.append("\n}")
  }.toString()