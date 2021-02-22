package com.rate.service

import com.rate.api.Asset
import com.rate.api.AwesomeApi
import com.rate.exception.NotFoundException
import com.rate.exception.ValidationException
import org.springframework.stereotype.Service

@Service
class AwesomeApiService(val awesomeApi: AwesomeApi) {

  fun getAllCoins(): Map<String, Asset> {

    return awesomeApi
      .makeApiCall("")
      .execute()
      .body() ?: throw NotFoundException()
  }

  fun getCoin(coin: String): Map<String, Asset> {

    val response = awesomeApi
      .makeApiCall(coin)
      .execute()

    if (response.isSuccessful) {
      return response.body() ?: throw NotFoundException()
    } else throw ValidationException()
  }
}
