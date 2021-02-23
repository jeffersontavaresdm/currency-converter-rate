package com.rate.repository

import com.rate.entity.Coin
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface CoinRepository : JpaRepository<Coin, Long> {

  fun findAllByTypeOrderByLastUpdateDateDesc(coin: String, pageable: Pageable): Page<Coin>

  fun findFirstByTypeAndLastUpdateDateOrderByLastUpdateDateDesc(code: String, today: LocalDate): Coin?

}