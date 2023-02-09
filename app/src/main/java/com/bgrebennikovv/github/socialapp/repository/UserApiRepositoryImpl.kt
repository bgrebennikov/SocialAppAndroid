package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.data.models.login.LoginResponse
import com.bgrebennikovv.github.socialapp.data.models.login.ResponseError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserApiRepositoryImpl : UserApiRepository, KoinComponent {

    private val api: HttpClient by inject()


    override suspend fun login(request: LoginRequest): BaseResponse<LoginResponse> {

        val r = api.post("/auth/login"){
            setBody(request)
        }.body<BaseResponse<LoginResponse>>()

        return try {
            BaseResponse.done(r.response, r.errors)
        } catch (e : Exception){
            BaseResponse.error(listOf(
                ResponseError(
                    message = e.message.toString()
                )
            ))
        }
    }


}