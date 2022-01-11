package com.rate.service

import com.rate.entity.Currency
import com.rate.entity.dto.CurrencyDTO
import com.rate.entity.dto.toDTO
import com.rate.repository.CurrencyRepository
import com.rate.utils.loggerFor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CurrencyConverterService(private val repository: CurrencyRepository) {

  private val logger = loggerFor()

  fun save(currency: Currency): Currency {
    return repository.save(currency)
  }

  fun getInfoList(currencyType: String, pageable: Pageable): Page<CurrencyDTO> {
    val page = repository.findAllByTypeOrderBySavedDateDesc(currencyType, pageable)

    return page.map { currency ->
      logger.info("Currency:\n{}", currency)
      currency.toDTO()
    }
  }

  fun getAll(): Set<CurrencyDTO> {
    val list = repository.findAll().map { currency ->
      logger.info("Currency:\n{}", currency)
      currency.toDTO()
    }
    return list.toSet()
  }
}