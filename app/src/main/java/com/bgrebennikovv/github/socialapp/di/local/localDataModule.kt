package com.bgrebennikovv.github.socialapp.di

import com.bgrebennikovv.github.socialapp.repository.DataStoreRepository
import com.bgrebennikovv.github.socialapp.repository.DataStoreRepositoryImpl
import org.koin.dsl.module

val localDataModule = module {
    single<DataStoreRepository> {
        DataStoreRepositoryImpl(get())
    }
}