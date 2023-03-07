package com.bgrebennikovv.github.socialapp.useCases.appSettings

import com.bgrebennikovv.github.socialapp.repository.AppSettingsRepository
import com.bgrebennikovv.github.socialapp.repository.DataStoreRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SetAppThemeOnStartUseCase() : KoinComponent {

    private val dataStoreRepository : DataStoreRepository by inject()
    private val appSettingsRepository: AppSettingsRepository by inject()

    suspend operator fun invoke(){
        dataStoreRepository.getAppThemeParam()?.let {
            appSettingsRepository.setAppTheme(it)
        }
    }

}