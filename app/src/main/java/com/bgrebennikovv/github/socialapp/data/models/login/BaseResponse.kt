package com.bgrebennikovv.github.socialapp.data.models.login

data class BaseResponse<out T>(
    val status: StatusResponse,
    val response: T?,
    val errors : List<ResponseError>
){


    companion object{
        fun <T> done(data: T?, errors: List<ResponseError>?) =
            BaseResponse(
                response = data,
                status = if (errors?.isEmpty() == true) StatusResponse.SUCCESS else StatusResponse.API_ERROR,
                errors = errors ?: listOf()
            )

        fun error(errors: List<ResponseError>? = null) =
            BaseResponse(
                response = null,
                status = StatusResponse.API_ERROR,
                errors = errors ?: listOf()
            )

        fun loading() =
            BaseResponse(
                response = null,
                status = StatusResponse.LOADING,
                errors = listOf()
            )
    }
}