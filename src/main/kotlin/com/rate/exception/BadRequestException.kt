package com.rate.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class BadRequestException : BaseException {
  constructor() : super("bad.request.exception")
  constructor(message: String) : super(message)
}