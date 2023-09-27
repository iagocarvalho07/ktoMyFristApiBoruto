package iagocarvalho.com.plugins

import iagocarvalho.com.di.KoinModel
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configuretionKoin(){
    install(Koin){
        slf4jLogger()
        modules(KoinModel)
    }
}