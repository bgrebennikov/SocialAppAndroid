package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.local.prefs.AppThemeParam

interface DataStoreRepository {

    suspend fun getAppThemeParam() : AppThemeParam?
    suspend fun setAppThemeParam(param: AppThemeParam)

    suspend fun getString(key: String) : String?
    suspend fun setString(key: String, value: String)

    suspend fun getAccessToken() : String?
    suspend fun setAccessToken(token: String)

    suspend fun getRefreshToken() : String?
    suspend fun setRefreshToken(token: String)

}