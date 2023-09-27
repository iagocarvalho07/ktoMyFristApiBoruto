package iagocarvalho.com

import iagocarvalho.com.models.ApiResponse
import iagocarvalho.com.plugins.*
import iagocarvalho.com.repository.HeroRepository
import iagocarvalho.com.repository.HeroRepostitoryImplementation
import iagocarvalho.com.repository.NEXT_PAGE_KEY
import iagocarvalho.com.repository.PREVIOUS_PAGE_KEY
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.*
import org.koin.ktor.ext.inject

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Boruto Api", bodyAsText())
        }
    }

    @Test
    fun `access al heroes end point assert correct Information`() = testApplication {
        val heroRepository = HeroRepostitoryImplementation()
        val pages = 1..5
        val heroes = listOf(
            heroRepository.page1,
            heroRepository.page2,
            heroRepository.page3,
            heroRepository.page4,
            heroRepository.page5
        )
        pages.forEach { page ->
            val response = client.get("/boruto/heroes?page=$page")
            assertEquals(
                expected = HttpStatusCode.OK,
                actual = response.status
            )
            val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
            val expected = ApiResponse(
                success = true,
                message = "ok",
                prevPage = calculatePage(page = page)["prevPage"],
                nextPage = calculatePage(page = page)["nextPage"],
                heroes = heroes[page - 1],
                lastUpdated = actual.lastUpdated

            )
            assertEquals(
                expected = expected,
                actual = actual
            )
        }


    }
    private fun calculatePage(page: Int) =
        mapOf(
            PREVIOUS_PAGE_KEY to if (page in 2..5) page.minus(1) else null,
            NEXT_PAGE_KEY to if (page in 1..4) page.plus(1) else null
        )
}
