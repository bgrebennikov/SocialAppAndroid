package com.bgrebennikovv.github.socialapp.repository

import android.content.Context
import com.bgrebennikovv.github.socialapp.R
import com.bgrebennikovv.github.socialapp.data.models.login.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.net.ConnectException

class UserApiRepositoryImpl(
    private val context: Context
) : UserApiRepository, KoinComponent {

    private val api: HttpClient by inject()

    private inline fun<reified T> respondError(errorText: Int) : BaseResponse<T>{
        return BaseResponse.error(listOf(
            ResponseError(
                message = context.resources.getString(errorText)
            )
        ))
    }

    private suspend inline fun <reified T : Any>makeRequest(endpoint: Endpoints, request: Any) : BaseResponse<T>{
        return try {
            val r = api.post(endpoint.path){
                setBody(request)
            }.body<BaseResponse<T>>()

            BaseResponse.done(r.response, r.errors)
        }
        catch (e: ConnectTimeoutException){
            respondError(R.string.ktor_connection_timeout)
        }
        catch (e: ConnectException){
            respondError(R.string.ktor_connection_exception)
        }
        catch (e : Exception){
            BaseResponse.error(listOf(
                ResponseError(
                    message = e.message.toString()
                )
            ))
        }
    }


    override suspend fun login(request: LoginRequest): BaseResponse<AuthResponse> {
        return makeRequest(
            endpoint = Endpoints.LOGIN,
            request = request,
        )
    }

    override suspend fun signUp(request: SignupRequest): BaseResponse<AuthResponse> {
        return makeRequest(
            endpoint = Endpoints.SIGNUP,
            request
        )
    }

    override suspend fun isEmailCanJoin(request: SignUpEmailCheckRequest): BaseResponse<SignUpEmailCheckResponse> {
        return makeRequest(
            endpoint = Endpoints.EMAIL_CHECK,
            request
        )

    }


    companion object{
        enum class Endpoints(val path: String){
            LOGIN("/auth/login"), SIGNUP("/auth/signup"),
            EMAIL_CHECK("auth/isEmailExists")
        }
    }


}