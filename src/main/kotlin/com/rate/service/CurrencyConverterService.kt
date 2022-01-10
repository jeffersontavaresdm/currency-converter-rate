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

  fun getInfoList(currencyType: String, pageable: Pageable): Page<CurrencyDTO> {
    val page = repository.findAllByTypeOrderBySavedDateDesc(currencyType, pageable)

    return if (currencyType == "all") {
      repository
        .findAll(pageable)
        .map { it.toDTO() }
    } else {
      page.map { it.toDTO() }
    }
  }
}