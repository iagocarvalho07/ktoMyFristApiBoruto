package iagocarvalho.com.routes

import iagocarvalho.com.repository.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.SearchHeroes() {
    val heroRepository: HeroRepository by inject()
    get("/boruto/heroes/search") {
        val name = call.request.queryParameters["name"]
        val apiResponse = heroRepository.searchHeros(name)
        call.respond(message = apiResponse, status = HttpStatusCode.OK)
    }
    get("/boruto/heroes/search/id") {
        val id = call.request.queryParameters["id"]
        val apiResponse = heroRepository.searchHerosbyId(id!!.toInt())
        call.respond(message = apiResponse, status = HttpStatusCode.OK)
    }
}