package com.bgrebennikovv.github.socialapp.data.models.login

data class AuthResponse(
    val id: String,
    val accessToken: String,
    val refreshToken: String,
    val lastUpdate: Long
)