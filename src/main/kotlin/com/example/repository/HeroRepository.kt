package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Event

interface HeroRepository {

    val heroes :Map<Int,List<Event>>
    val page1: List<Event>
    val page2: List<Event>



    suspend fun getAllHeroes(page:Int=1):ApiResponse
    suspend fun searchHeroes(name:String?):ApiResponse
}