package com.bgrebennikovv.github.socialapp.repository

import androidx.appcompat.app.AppCompatDelegate
import com.bgrebennikovv.github.socialapp.data.local.prefs.AppThemeParam

class AppSettingsRepositoryImpl : AppSettingsRepository {
    override suspend fun setAppTheme(theme: AppThemeParam) {
        when(theme){
            AppThemeParam.DARK -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
            AppThemeParam.LIGHT -> AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
            AppThemeParam.DEFAULT -> {
                // Follow system
            }
        }
    }

}