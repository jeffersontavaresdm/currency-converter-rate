package com.rate.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AwesomeApi {

  @GET("/json/{currency}")
  fun makeApiCall(

    @Path("currency") currency: String

  ): Call<Map<String, Asset>>
}