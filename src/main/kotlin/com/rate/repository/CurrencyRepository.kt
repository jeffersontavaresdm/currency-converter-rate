package com.rate.repository

import com.rate.entity.Currency
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface CurrencyRepository : JpaRepository<Currency, Long> {
  fun findAllByTypeOrderBySavedDateDesc(currency: String, pageable: Pageable): Page<Currency>
  fun findByTypeAndSavedDate(currency: String, today: LocalDate): Currency?

  @Query(
    value = """
    select c from Currency as c
    order by c.id asc
  """
  )
  fun getAllOrderById(): List<Currency>
}