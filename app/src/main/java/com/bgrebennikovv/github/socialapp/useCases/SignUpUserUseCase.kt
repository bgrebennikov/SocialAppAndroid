package com.bgrebennikovv.github.socialapp.useCases

import com.bgrebennikovv.github.socialapp.data.models.login.AuthResponse
import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.SignupRequest
import com.bgrebennikovv.github.socialapp.repository.DataStoreRepository
import com.bgrebennikovv.github.socialapp.repository.UserApiRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUserUseCase : KoinComponent {

    private val apiService: UserApiRepository by inject()
    private val dataStoreRepository : DataStoreRepository by inject()

    suspend operator fun invoke(request: SignupRequest) : BaseResponse<AuthResponse>{
        return apiService.signUp(request).also { tokens ->
            tokens.response?.let {
                dataStoreRepository.setAccessToken(it.accessToken)
                dataStoreRepository.setRefreshToken(it.refreshToken)
            }
        }
    }

}