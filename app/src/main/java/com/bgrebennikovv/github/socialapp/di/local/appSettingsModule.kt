package com.bgrebennikovv.github.socialapp.di

import com.bgrebennikovv.github.socialapp.repository.AppSettingsRepository
import com.bgrebennikovv.github.socialapp.repository.AppSettingsRepositoryImpl
import org.koin.dsl.module

val appSettingsModule = module {
    single<AppSettingsRepository> {
        AppSettingsRepositoryImpl()
    }
}