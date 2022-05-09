package com.rate.adapter

import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class SpringBootEssentialsAdapter : WebMvcConfigurer {

  override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {

    val pageableResolver = PageableHandlerMethodArgumentResolver()

    pageableResolver.setFallbackPageable(PageRequest.of(0, 10))

    argumentResolvers.add(pageableResolver)
  }
}