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


    private suspend inline fun <reified T : Any>makeRequest(endpoint: String, request: Any) : BaseResponse<T>{
        val r = api.post(endpoint){
            setBody(request)
        }.body<BaseResponse<T>>()

        return try {
            BaseResponse.done(r.response, r.errors)
        } catch (e : Exception){
            e.printStackTrace()
            BaseResponse.error(listOf(
                ResponseError(
                    message = e.message.toString()
                )
            ))
        }
    }


    override suspend fun login(request: LoginRequest): BaseResponse<LoginResponse> {
        return makeRequest(
            endpoint = "/auth/login",
            request = request,
        )
    }


}