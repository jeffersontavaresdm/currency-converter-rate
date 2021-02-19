package com.rate.repository

import com.rate.entity.Coin
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface CoinRepository : JpaRepository<Coin, Long> {

  fun findAllByTypeOrderByLastUpdateTimeDesc(coin: String): List<Coin>

  fun findFirstByTypeAndLastUpdateTimeOrderByLastUpdateTimeDesc(code: String, today: LocalDate): Coin?

}