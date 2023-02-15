package com.bgrebennikovv.github.socialapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgrebennikovv.github.socialapp.data.models.login.AuthResponse
import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.useCases.LoginUserUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val loginResult : MutableLiveData<BaseResponse<AuthResponse>> = MutableLiveData<BaseResponse<AuthResponse>>()

    fun getLoginResult() = loginResult

    fun login(email: String, password: String) {
        loginResult.value = BaseResponse.loading()
        viewModelScope.launch {

            loginResult.value = loginUserUseCase.invoke(
                LoginRequest(
                    email, password
                )
            )

        }
    }

}