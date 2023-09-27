package iagocarvalho.com.di

import iagocarvalho.com.repository.HeroRepository
import iagocarvalho.com.repository.HeroRepostitoryImplementation
import org.koin.dsl.module

val KoinModel = module {
    single<HeroRepository> {
        HeroRepostitoryImplementation()
    }
}