package com.bgrebennikovv.github.socialapp.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bgrebennikovv.github.socialapp.common.DATA_STORE_NAME
import com.bgrebennikovv.github.socialapp.common.KEY_ACCESS_TOKEN
import com.bgrebennikovv.github.socialapp.common.KEY_APP_THEME
import com.bgrebennikovv.github.socialapp.common.KEY_REFRESH_TOKEN
import com.bgrebennikovv.github.socialapp.data.local.prefs.AppThemeParam
import kotlinx.coroutines.flow.first

class DataStoreRepositoryImpl(
    private val context: Context
) : DataStoreRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    override suspend fun getAppThemeParam(): AppThemeParam? {
        return context.dataStore.data.first()[stringPreferencesKey(KEY_APP_THEME)]?.let {
            AppThemeParam.valueOf(
                it
            )
        }
    }

    override suspend fun setAppThemeParam(param: AppThemeParam) {
        with(context){
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey(KEY_APP_THEME)] = param.name
            }
        }
    }

    override suspend fun getString(key: String): String? {
        return context.dataStore.data.first()[
                stringPreferencesKey(key)
        ]
    }

    override suspend fun setString(key: String, value: String) {
        with(context){
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey(key)] = value
            }
        }
    }

    override suspend fun getAccessToken(): String? {
        return getString(KEY_ACCESS_TOKEN)
    }

    override suspend fun setAccessToken(token: String) {
        setString(KEY_ACCESS_TOKEN, token)
    }

    override suspend fun getRefreshToken(): String? {
        return getString(KEY_REFRESH_TOKEN)
    }

    override suspend fun setRefreshToken(token: String) {
        setString(KEY_REFRESH_TOKEN, token)
    }
}