package com.rate.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.rate.entity.dto.CurrencyTypesData
import com.rate.enum.CurrencyTypes
import com.rate.utils.convertToJson
import org.springframework.stereotype.Service

@Service
class CurrencyTypesService(private val mapper: ObjectMapper) {

  fun getTypes(): CurrencyTypesData {
    val string = CurrencyTypes.values().convertToJson()

    return string.let {
      val data = mapper
        .findAndRegisterModules()
        .readValue(it, CurrencyTypesData::class.java)

      CurrencyTypesData(
        ALL = data.ALL,
        USD = data.USD,
        CAD = data.CAD,
        GBP = data.GBP,
        ARS = data.ARS,
        BTC = data.BTC,
        LTC = data.LTC,
        EUR = data.EUR,
        JPY = data.JPY,
        CHF = data.CHF,
        AUD = data.AUD,
        CNY = data.CNY,
        ILS = data.ILS,
        ETH = data.ETH,
        XRP = data.XRP,
        DOGE = data.DOGE,
      )
    }
  }
}