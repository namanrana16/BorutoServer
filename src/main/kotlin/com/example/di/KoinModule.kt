package com.example.di

import com.example.repository.HeroRepository
import com.example.repository.HeroRepository2
import com.example.repository.HeroRepositoryImpl
import com.example.repository.HeroRepositoryImpl2
import org.koin.dsl.module

val koinModule= module {
    single<HeroRepository>{
        HeroRepositoryImpl()
    }

    single<HeroRepository2>{
        HeroRepositoryImpl2()
    }
}