package iagocarvalho.com.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*

import io.netty.handler.codec.DefaultHeaders
import java.time.Duration

fun Application.configureDefaultHeaders() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
        header(name = HttpHeaders.CacheControl, value = "public, Max-age = $oneYearInSeconds, immutable")
    }
}