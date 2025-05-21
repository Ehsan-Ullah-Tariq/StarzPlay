package com.android.starzplay.utils.localehelper

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.android.starzplay.di.modules.dataStore
import com.android.starzplay.presentation.base.BaseActivity.Companion.LANGUAGE
import com.android.starzplay.presentation.base.BaseActivity.Companion.LOCALE_EN
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LanguageManager @Inject constructor(@ApplicationContext val context: Context) {
    private val dataStore = context.dataStore
    val languageFlow: Flow<String> =
        dataStore.data.map { it[stringPreferencesKey(LANGUAGE)] ?: LOCALE_EN }
    suspend fun setLanguage(lang: String) {
        dataStore.edit { it[stringPreferencesKey(LANGUAGE)] = lang }
    }
}
