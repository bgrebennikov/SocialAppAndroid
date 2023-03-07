package com.bgrebennikovv.github.socialapp.data.models.login

data class ResponseError(
    val message: String,
    val field: String? = null,
    val type: ResponseErrorType = ResponseErrorType.OTHER
)

enum class ResponseErrorType{
    WRONG_CREDENTIALS, OTHER
}