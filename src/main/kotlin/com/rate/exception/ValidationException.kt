package com.rate.exception

class ValidationException : BaseException {
  constructor() : super("validation.exception")
  constructor(message: String) : super(message)
}