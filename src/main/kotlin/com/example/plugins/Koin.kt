package com.example.plugins

import com.example.di.koinModule
import io.ktor.application.*
import org.koin.core.logger.Level
import org.koin.logger.slf4jLogger

fun Application.configureKoin(){

    install(org.koin.ktor.ext.Koin){
        slf4jLogger(level = Level.ERROR)
        modules(koinModule)
    }
}