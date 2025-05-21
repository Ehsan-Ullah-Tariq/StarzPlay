package com.android.starzplay.presentation.base

import android.content.Context
import androidx.activity.ComponentActivity
import com.android.starzplay.utils.localehelper.LanguageManager
import com.android.starzplay.utils.localehelper.LocaleHelper
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

abstract class BaseActivity : ComponentActivity() {

    companion object{
        const val LOCALE_EN = "en"
        const val LOCALE_AR = "ar"
        const val LANGUAGE = "language"
    }

    override fun attachBaseContext(newBase: Context) {
        val lang = runBlocking {
            LanguageManager(newBase).languageFlow.first()
        }
        val context = LocaleHelper.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }
}