package ru.hedgdifuse.lookapp.shared.di

import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.hedgdifuse.lookapp.shared.Constants.API.DOMAIN
import ru.hedgdifuse.lookapp.shared.presentation.LaunchManager
import ru.hedgdifuse.lookapp.shared.router.LookRouterI
import ru.hedgdifuse.lookapp.shared.router.impl.LookRouter
import ru.hedgdifuse.lookapp.shared.usecase.impl.PingUseCase
import ru.hedgdifuse.lookapp.shared.usecase.impl.ProfileUseCase
import ru.hedgdifuse.lookapp.shared.usecase.impl.SendCodeUseCase

fun startMultiplatformKoin(platformFeature: KoinApplication.() -> Unit) = startKoin {
    modules(
        KtorModule,
        ToolsModule,
        UseCasesModule
    )

    platformFeature()
}

private val KtorModule = module {
    factory {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            val sidCookie = get<Settings>().getStringOrNull("sid")

            // Save cookies
            install(HttpCookies) {
                storage = if (sidCookie == null)
                    AcceptAllCookiesStorage()
                else
                    ConstantCookiesStorage(
                        Cookie("sid", sidCookie,
                            path = "/",
                            expires = GMTDate(),
                            domain = DOMAIN
                        )
                    )
            }
        }
    }
}

private val ToolsModule = module {
    factory { LookRouter(get()) } bind LookRouterI::class
    factory { Settings() } bind Settings::class
    factory { LaunchManager(get()) }
}

private val UseCasesModule = module {
    factory { SendCodeUseCase(get()) }
    factory { PingUseCase(get()) }
    factory { ProfileUseCase(get()) }
}