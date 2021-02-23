package com.rate.exception

class BadRequestException : BaseException {

  constructor() : super("bad.request.exception")

  constructor(message: String) : super(message)

}