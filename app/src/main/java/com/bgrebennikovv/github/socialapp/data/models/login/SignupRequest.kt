package com.bgrebennikovv.github.socialapp.data.models.login

data class SignupRequest(
    var email: String,
    val password: String,
    val firstName: String,
    val lastName : String,
)
