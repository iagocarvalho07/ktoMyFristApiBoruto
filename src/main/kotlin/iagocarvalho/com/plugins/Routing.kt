package iagocarvalho.com.plugins

import iagocarvalho.com.routes.SearchHeroes
import iagocarvalho.com.routes.getAllHeroies
import iagocarvalho.com.routes.root
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllHeroies()
        staticResources( "/images", "images"){}
        SearchHeroes()
    }
}
