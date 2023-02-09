package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.data.models.login.LoginResponse

interface UserApiRepository {
    suspend fun login(request: LoginRequest) : BaseResponse<LoginResponse>
}