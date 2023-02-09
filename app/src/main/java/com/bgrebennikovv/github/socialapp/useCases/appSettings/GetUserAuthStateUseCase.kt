package com.bgrebennikovv.github.socialapp.useCases.appSettings

import com.bgrebennikovv.github.socialapp.repository.DataStoreRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserAuthStateUseCase : KoinComponent {

    private val dataStoreRepository: DataStoreRepository by inject()

    suspend operator fun invoke() : Boolean{
        return dataStoreRepository.getAccessToken() != null
    }

}