package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.getAllHeroes2
import com.example.routes.root
import com.example.routes.searchHeroes
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.request.*
import javax.naming.AuthenticationException

fun Application.configureRouting() {

    routing {
        root()
       getAllHeroes()
     //   getAllHeroes2()
        searchHeroes()

        get("/test2")
        {
            throw AuthenticationException()
        }
        static ( "/images" ){
            resources("images")
        }
    }
}
