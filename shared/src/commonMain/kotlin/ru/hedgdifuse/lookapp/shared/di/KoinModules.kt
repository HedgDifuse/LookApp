package ru.hedgdifuse.lookapp.shared.di

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.hedgdifuse.lookapp.shared.settings
import ru.hedgdifuse.lookapp.shared.usecase.SendCodeUseCase
import shared.router.impl.LookRouter

fun startMultiplatformKoin(platformFeature: KoinApplication.() -> Unit) = startKoin {
    platformFeature()

    modules(
        KtorModule,
        ToolsModule,
        UseCasesModule
    )
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
        }
    }
}

private val ToolsModule = module {
    factory { LookRouter(get(), get()) }
    factory { settings() }
}

private val UseCasesModule = module {
    factory { SendCodeUseCase(get()) }
}