package com.example


import com.example.models.ApiResponse
import com.example.plugins.configureRouting
import com.example.repository.HeroRepository
import com.example.repository.NEXT_PAGE_KEY
import com.example.repository.PREVIOUS_PAGE_KEY
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.assertEquals

class ApplicationTest {

    private val heroRepository: HeroRepository by inject(HeroRepository::class.java)

    @ExperimentalSerializationApi
    @Test
    fun testRoot() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Welcome to MidShounen", response.content)
            }
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun testAllHeroes(){

        withTestApplication(moduleFunction = Application::module) {

            val pages = 1..5
            val heroes = listOf(
                heroRepository.page1,
                heroRepository.page2
            )
            pages.forEach { page->
            handleRequest(HttpMethod.Get, "/boruto/heroes?page=$page").apply {
                assertEquals(HttpStatusCode.OK, response.status())

                val expected = ApiResponse(
                    success = true, message = "ok", prevPage = calculatePage(page)["prevPage"], nextPage = calculatePage(page)["nextPage"],
                    heroes = heroes[page-1]
                )

                val actualResult = Json.decodeFromString<ApiResponse>(response.content.toString())

                assertEquals(expected, actualResult)
            }
        }
        }
    }




    @ExperimentalSerializationApi
    @Test
    fun testSearchHeroes(){

        withTestApplication(moduleFunction = Application::module) {

                handleRequest(HttpMethod.Get, "/boruto/heroes/search?name=sas").apply {
                    assertEquals(HttpStatusCode.OK, response.status())


                    val actualResult = Json.decodeFromString<ApiResponse>(response.content.toString()).heroes.size
                     assertEquals(expected=1,actual=actualResult)

                }

        }
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









}