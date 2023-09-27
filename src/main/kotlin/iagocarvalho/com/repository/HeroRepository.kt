package iagocarvalho.com.repository

import iagocarvalho.com.models.ApiResponse
import iagocarvalho.com.models.Hero

interface HeroRepository {

    val heroes: Map<Int, List<Hero>>
    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>


    suspend fun getAlHerroes(page: Int = 1): ApiResponse
    suspend fun searchHeros(page: String?): ApiResponse
    suspend fun searchHerosbyId(id: Int?): ApiResponse

}