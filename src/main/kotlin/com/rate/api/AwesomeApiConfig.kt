package com.rate.api

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class AwesomeApiConfig {

  @Bean
  fun awesomeApi(): AwesomeApi {

    val mapper = ObjectMapper()
      .findAndRegisterModules()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return Retrofit
      .Builder()
      .baseUrl("https://economia.awesomeapi.com.br/")
      .addConverterFactory(JacksonConverterFactory.create(mapper))
      .build()
      .create(AwesomeApi::class.java)
  }
}