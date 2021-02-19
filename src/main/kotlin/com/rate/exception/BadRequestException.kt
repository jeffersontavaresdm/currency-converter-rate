package com.rate.exception

class BadRequestException : BaseException {

  constructor() : super("bad.request")

  constructor(message: String) : super(message)
}