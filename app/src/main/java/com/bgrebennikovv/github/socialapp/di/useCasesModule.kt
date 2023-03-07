package com.bgrebennikovv.github.socialapp.di

import com.bgrebennikovv.github.socialapp.useCases.SignUpEmailCheckUseCase
import com.bgrebennikovv.github.socialapp.useCases.LoginUserUseCase
import com.bgrebennikovv.github.socialapp.useCases.SignUpUserUseCase
import com.bgrebennikovv.github.socialapp.useCases.appSettings.GetUserAuthStateUseCase
import com.bgrebennikovv.github.socialapp.useCases.appSettings.LogoutUserUseCase
import com.bgrebennikovv.github.socialapp.useCases.appSettings.SetAppThemeOnStartUseCase
import org.koin.dsl.module


val useCasesModule = module {
    single{
        LoginUserUseCase()
    }
    single {
        SetAppThemeOnStartUseCase()
    }
    single {
        GetUserAuthStateUseCase()
    }
    single {
        LogoutUserUseCase()
    }
    single {
        SignUpUserUseCase()
    }
    single {
        SignUpEmailCheckUseCase()
    }
}