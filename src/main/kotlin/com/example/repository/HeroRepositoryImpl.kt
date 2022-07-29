package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Event


const val NEXT_PAGE_KEY ="nextPage"
const val PREVIOUS_PAGE_KEY="prevPage"

class HeroRepositoryImpl():HeroRepository {


    override val heroes: Map<Int, List<Event>> by lazy {

        mapOf(
            1 to page1, 2 to page2
        )
    }


    override val page1 = listOf(
        Event(
            id = 1,
            name = "Parade",
            image = "/images/event1.jpg",
            about = "Iz a parade",
            rating = 5.0,

            month = "July",
            day = "23rd"
        ),
        Event(
            id = 2,
            name = "College Fest",
            image = "/images/event2.jpg",
            about = "Iz a collez fest",
            rating = 5.0,
            month = "Oct",
            day = "10th",
        ),
        Event(
            id = 3,
            name = "Mela",
            image = "/images/event3.jpg",
            about = "Mela he 20 Rz entry",
            rating = 3.0,
            month = "Mar",
            day = "28th",

        )
    )
    override val page2 = listOf(
        Event(
            id = 4,
            name = "Photo-shoot",
            image = "/images/event4.jpg",
            about = "Iz a photo shoot,get your DP",
            rating = 4.9,
            month = "Mar",
            day = "27th",

        ),
        Event(
            id = 5,
            name = "Balloon ride",
            image = "/images/event5.jpg",
            about = "Gubbare me uddo",
            rating = 4.9,

            month = "Mar",
            day = "31st",

        ),
        Event(
            id = 6,
            name = "Marathon",
            image = "/images/event6.jpg",
            about = "Bhaagooo",
            rating = 4.9,
            month = "Jul",
            day = "25th",

        )
    )


    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(page = page)[PREVIOUS_PAGE_KEY],
            nextPage = calculatePage(page = page)[NEXT_PAGE_KEY],
            heroes = heroes[page]!!,
            lastUpdated = System.currentTimeMillis()
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return mapOf(PREVIOUS_PAGE_KEY to prevPage, NEXT_PAGE_KEY to nextPage)
    }

    override suspend fun searchHeroes(name: String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            heroes = findHeroes(query = name)
        )
    }

    private fun findHeroes(query: String?): List<Event> {
        val founded = mutableListOf<Event>()
        return if (!query.isNullOrEmpty()) {
            heroes.forEach { (_, heroes) ->
                heroes.forEach { hero ->
                    if (hero.name.lowercase().contains(query.lowercase())) {
                        founded.add(hero)
                    }
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}