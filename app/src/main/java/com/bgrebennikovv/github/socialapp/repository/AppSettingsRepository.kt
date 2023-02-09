package com.bgrebennikovv.github.socialapp.repository

import com.bgrebennikovv.github.socialapp.data.local.prefs.AppThemeParam

interface AppSettingsRepository {

    suspend fun setAppTheme(theme: AppThemeParam)

}