package com.rate.service

import com.rate.entity.Currency
import com.rate.entity.dto.CurrencyDTO
import com.rate.entity.dto.toDTO
import com.rate.repository.CurrencyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CurrencyConverterService(private val repository: CurrencyRepository) {

  fun save(currency: Currency): Currency {
    return repository.save(currency)
  }

  fun getInfoList(type: String, pageable: Pageable): Page<CurrencyDTO> {
    return repository
      .findAllByTypeOrderBySavedDateDesc(type, pageable)
      .map { it.toDTO() }
  }

  fun getAll(): MutableMap<String, CurrencyDTO> {
    return mutableMapOf<String, CurrencyDTO>()
      .apply {
        repository.getAllOrderById()
          .forEach {
            this[it.type] = it.toDTO()
          }
      }
  }
}