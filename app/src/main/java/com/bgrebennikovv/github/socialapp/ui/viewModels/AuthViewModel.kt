package com.bgrebennikovv.github.socialapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgrebennikovv.github.socialapp.data.models.login.AuthResponse
import com.bgrebennikovv.github.socialapp.data.models.login.BaseResponse
import com.bgrebennikovv.github.socialapp.data.models.login.LoginRequest
import com.bgrebennikovv.github.socialapp.useCases.LoginUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed class Event{
    object GoNext : Event()
}

class AuthViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val loginResult : MutableLiveData<BaseResponse<AuthResponse>?> = MutableLiveData<BaseResponse<AuthResponse>?>()

    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var password: String? = null

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun goNext(){
        viewModelScope.launch {
            eventChannel.send(Event.GoNext)
        }
    }

    fun login(email: String, password: String) {
        loginResult.value = BaseResponse.loading()
        viewModelScope.launch {

            loginResult.value = loginUserUseCase.invoke(
                LoginRequest(
                    email, password
                )
            )
            loginResult.postValue(null)

        }
    }

    fun getLoginResult() = loginResult

}