package com.bgrebennikovv.github.socialapp.useCases

import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.SignUpEmailCheckRequest
import com.bgrebennikovv.github.socialapp.data.models.login.SignUpEmailCheckResponse
import com.bgrebennikovv.github.socialapp.repository.UserApiRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpEmailCheckUseCase : KoinComponent {

    private val apiService: UserApiRepository by inject()

    suspend operator fun invoke(email: String): BaseResponse<SignUpEmailCheckResponse> {
        return apiService.isEmailCanJoin(
            SignUpEmailCheckRequest(email)
        )
    }

}