package com.rate.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AwesomeApi {

  @GET("/json/{coin}")
  fun makeApiCall(

    @Path("coin") coin: String

  ): Call<Map<String, Asset>>
}