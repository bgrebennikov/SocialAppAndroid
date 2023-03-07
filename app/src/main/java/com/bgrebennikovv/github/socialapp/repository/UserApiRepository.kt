package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.models.login.*

interface UserApiRepository {
    suspend fun login(request: LoginRequest) : BaseResponse<AuthResponse>
    suspend fun signUp(request: SignupRequest) : BaseResponse<AuthResponse>
    suspend fun isEmailCanJoin(request: SignUpEmailCheckRequest) : BaseResponse<SignUpEmailCheckResponse>
}