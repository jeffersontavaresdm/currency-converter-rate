package com.rate.service

import com.rate.entity.Currency
import com.rate.repository.CurrencyRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CurrencyRateService(private val currencyRepository: CurrencyRepository) {

  fun update(currency: Currency): Currency {
    return currencyRepository.save(currency)
  }

  fun getInfoList(currencyType: String, pageable: Pageable): Page<Currency> {
    return currencyRepository.findAllByTypeOrderBySavedDateDesc(currencyType, pageable)
  }
}