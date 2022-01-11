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

  fun getInfoList(type: String, pageable: Pageable): Page<CurrencyDTO> {
    val page = repository.findAllByTypeOrderBySavedDateDesc(type, pageable)

    return page.map { currency ->
      logger.info("Currency:\n{}", currency)
      currency.toDTO()
    }
  }

  fun getAll(): MutableMap<String, CurrencyDTO> {
    val map = mutableMapOf<String, CurrencyDTO>()
    repository.getAllOrderById().forEach { currency ->
      logger.info("Currency:\n{}", currency)
      map[currency.type] = currency.toDTO()
    }
    return map
  }
}