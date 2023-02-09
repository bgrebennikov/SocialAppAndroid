package com.bgrebennikovv.github.socialapp.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgrebennikovv.github.socialapp.useCases.appSettings.GetUserAuthStateUseCase
import com.bgrebennikovv.github.socialapp.useCases.appSettings.SetAppThemeOnStartUseCase
import kotlinx.coroutines.launch

class AppSettingsViewModel(
    private val setAppThemeOnStartUseCase: SetAppThemeOnStartUseCase,
    private val getUserAuthStateUseCase: GetUserAuthStateUseCase
) : ViewModel() {

    private val userAuthState = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            setAppThemeOnStartUseCase.invoke()
            checkUserAuthState()
        }
    }

    fun userIsAuthenticated() = userAuthState

    private suspend fun checkUserAuthState(){
        userAuthState.value = getUserAuthStateUseCase.invoke()
    }

}