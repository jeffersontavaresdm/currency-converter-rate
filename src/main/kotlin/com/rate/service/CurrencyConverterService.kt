package com.rate.service

import com.rate.entity.Currency
import com.rate.entity.dto.CurrencyDTO
import com.rate.repository.CurrencyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CurrencyConverterService(private val repository: CurrencyRepository) {

  fun save(currency: Currency): Currency {
    return repository.save(currency)
  }

  fun getInfoList(currencyType: String, pageable: Pageable): Page<CurrencyDTO> {
    val page = repository.findAllByTypeOrderBySavedDateDesc(currencyType, pageable)

    return page.map { currency ->
      CurrencyDTO(
        currencyType = currency.type,
        name = currency.name,
        maxValue = currency.maxValue,
        minValue = currency.minValue,
        date = currency.savedDate,
      )
    }
  }

  fun getAll(pageable: Pageable): Page<CurrencyDTO> {
    return repository
      .findAll(pageable)
      .map { currency ->
        CurrencyDTO(
          currencyType = currency.type,
          name = currency.name,
          maxValue = currency.maxValue,
          minValue = currency.minValue,
          date = currency.savedDate,
        )
      }
  }
}