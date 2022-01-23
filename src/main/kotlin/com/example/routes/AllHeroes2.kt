package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository2
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.lang.NumberFormatException

fun Route.getAllHeroes2(){

    val heroRepository2: HeroRepository2 by inject()

    get("/boruto/heroes"){

        try {
            val page = call.request.queryParameters["page"]?.toInt()?:1
            val limit =call.request.queryParameters["limit"]?.toInt() ?: 4

            val apiResponse=heroRepository2.getAllHeroes(page=page, limit = limit)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: NumberFormatException){
            call.respond(
                message = ApiResponse(success = false, message = "Number Allowed"),
                status = HttpStatusCode.BadRequest
            )
        }
        catch (e:IllegalArgumentException){
            call.respond(
                message = ApiResponse(success = false, message = "Not found heroes"),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}