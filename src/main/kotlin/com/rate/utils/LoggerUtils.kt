package com.rate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T : Any> T.loggerFor(): Logger = LoggerFactory.getLogger(this::class.java.canonicalName)