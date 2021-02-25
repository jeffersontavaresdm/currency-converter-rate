package com.rate.service

import com.rate.entity.Coin
import com.rate.repository.CoinRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CoinRateService(private val coinRepository: CoinRepository) {

  fun update(coin: Coin): Coin {
    return coinRepository.save(coin)
  }

  fun currencyValue(coin: String, page: Pageable): Page<Coin> {
    return coinRepository.findAllByTypeOrderBySavedDateDesc(coin, page)
  }
}