package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.data.models.login.AuthResponse
import com.bgrebennikovv.github.socialapp.data.models.login.SignupRequest

interface UserApiRepository {
    suspend fun login(request: LoginRequest) : BaseResponse<AuthResponse>
    suspend fun signUp(request: SignupRequest) : BaseResponse<AuthResponse>
}