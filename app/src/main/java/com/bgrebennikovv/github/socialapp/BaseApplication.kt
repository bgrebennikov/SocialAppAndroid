package com.bgrebennikovv.github.socialapp

import android.app.Application
import com.bgrebennikovv.github.socialapp.di.*
import com.bgrebennikovv.github.socialapp.repository.UserApiRepository
import com.bgrebennikovv.github.socialapp.repository.UserApiRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

internal class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                ktorClientModule,
                module {
                    single<UserApiRepository> {
                        UserApiRepositoryImpl(get())
                    }
                },
                viewModelsModule,
                useCasesModule,
                localDataModule,
                appSettingsModule
            )
        }

    }

}