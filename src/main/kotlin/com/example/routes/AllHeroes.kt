package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllHeroes(){

    val heroRepository:HeroRepository by inject()

    get("/proxima/events"){

        try {
            val page = call.request.queryParameters["page"]?.toInt()?:1
            require(page in 1..5)

            val apiResponse=heroRepository.getAllHeroes(page=page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e:NumberFormatException){
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