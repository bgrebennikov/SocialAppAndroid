package com.bgrebennikovv.github.socialapp.di

import com.bgrebennikovv.github.socialapp.common.BASE_URL
import com.bgrebennikovv.github.socialapp.common.PORT
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import org.koin.dsl.module


val ktorClientModule = module {
    single {
        HttpClient(OkHttp) {

            defaultRequest {
                host = BASE_URL
                port = PORT
                contentType(ContentType.Application.Json)
            }

            install(ContentNegotiation){
                gson()
            }

        }
    }
}