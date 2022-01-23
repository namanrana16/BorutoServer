package com.example.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.netty.handler.codec.DefaultHeaders
import java.time.Duration

fun Application.configureDefaultHeader(){
    install(io.ktor.features.DefaultHeaders)
    {
        val oneYearInSeconds= Duration.ofDays(365).seconds
        header(name = HttpHeaders.CacheControl,
        value = "public,max-age=$oneYearInSeconds,immutable")
    }
}