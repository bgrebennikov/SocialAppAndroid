package com.bgrebennikovv.github.socialapp.useCases

import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.data.models.login.LoginResponse
import com.bgrebennikovv.github.socialapp.repository.DataStoreRepository
import com.bgrebennikovv.github.socialapp.repository.UserApiRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginUserUseCase : KoinComponent {

    private val apiService : UserApiRepository by inject()
    private val dataStoreRepository : DataStoreRepository by inject()

    suspend operator fun invoke(request: LoginRequest) : BaseResponse<LoginResponse>{
        return apiService.login(request).also { data ->
            data.response?.let {
                dataStoreRepository.setAccessToken(it.accessToken)
                dataStoreRepository.setRefreshToken(it.refreshToken)
            }
        }
    }

}