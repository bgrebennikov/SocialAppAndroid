package com.bgrebennikovv.github.socialapp.di

import com.bgrebennikovv.github.socialapp.ui.viewModels.AppSettingsViewModel
import com.bgrebennikovv.github.socialapp.ui.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { AppSettingsViewModel(get(), get()) }
}