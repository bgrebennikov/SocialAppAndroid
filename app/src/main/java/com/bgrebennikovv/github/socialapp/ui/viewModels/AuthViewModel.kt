package com.bgrebennikovv.github.socialapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgrebennikovv.github.socialapp.data.models.login.*
import com.bgrebennikovv.github.socialapp.useCases.LoginUserUseCase
import com.bgrebennikovv.github.socialapp.useCases.SignUpEmailCheckUseCase
import com.bgrebennikovv.github.socialapp.useCases.SignUpUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class SignUpViewPagerEvent {
    object GoNext : SignUpViewPagerEvent()
}

sealed class EmailCheckEvent {
    object OnSuccess: EmailCheckEvent()
    object OnFailure: EmailCheckEvent()
}

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val signUpUserUseCase: SignUpUserUseCase,
    private val signUpEmailCheckUseCase: SignUpEmailCheckUseCase
) : ViewModel() {

    private val authResult: MutableLiveData<BaseResponse<AuthResponse>?> =
        MutableLiveData<BaseResponse<AuthResponse>?>()
    
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var password: String? = null

    private val signUpViewPagerEventChannel = Channel<SignUpViewPagerEvent>(Channel.BUFFERED)
    val signupViewPagerFlow = signUpViewPagerEventChannel.receiveAsFlow()

    private val emailCheckEventChannel = Channel<EmailCheckEvent>(Channel.BUFFERED)
    val emailCheckFlow = emailCheckEventChannel.receiveAsFlow()


    fun goNext() {
        viewModelScope.launch {
            signUpViewPagerEventChannel.send(SignUpViewPagerEvent.GoNext)
        }
    }

    fun login(email: String, password: String) {
        authResult.value = BaseResponse.loading()
        viewModelScope.launch {

            authResult.value = loginUserUseCase.invoke(
                LoginRequest(
                    email, password
                )
            )
            authResult.postValue(null)

        }
    }

    fun checkEmailExists(email: String){
        viewModelScope.launch {
            val result = signUpEmailCheckUseCase.invoke(email)
            if(result.response?.success == true) emailCheckEventChannel.send(EmailCheckEvent.OnSuccess)
            else emailCheckEventChannel.send(EmailCheckEvent.OnFailure)
        }
    }

    fun signUp() {
        viewModelScope.launch {

            authResult.value = signUpUserUseCase.invoke(
                SignupRequest(
                    email, password, firstName, lastName
                )
            )

        }
    }

    fun getAuthResult() = authResult

}