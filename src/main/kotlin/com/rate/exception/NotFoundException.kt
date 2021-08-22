package com.rate.exception

class NotFoundException : BaseException {
  constructor() : super("not.found.exception")
  constructor(message: String) : super(message)
}