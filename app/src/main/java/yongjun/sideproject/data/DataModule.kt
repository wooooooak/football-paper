package yongjun.sideproject.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import yongjun.sideproject.network.AppHttpException
import yongjun.sideproject.network.BadConnectionException
import yongjun.sideproject.ui.common.ErrorPayload

@Module
@ComponentScan
class DataModule {
    @Single
    fun provideHttpClient(): HttpClient = HttpClient(CIO) {
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, _ ->
                throw when (cause) {
                    is ResponseException -> {
                        val errorPayload = cause.response.body<ErrorPayload>()
                        AppHttpException(cause, errorPayload)
                    }

                    else -> BadConnectionException(cause)
                }
            }
        }
        defaultRequest {
            url("https://api.football-data.org/v4/")
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                },
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 20000
            connectTimeoutMillis = 10000
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}
