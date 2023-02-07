package com.bgrebennikovv.github.socialapp.data.models.login

data class LoginResponse(
    val id: String,
    val accessToken: String,
    val refreshToken: String,
    val lastUpdate: Long
)