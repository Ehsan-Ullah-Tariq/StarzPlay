package com.android.starzplay.presentation.ui.activites

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.android.starzplay.presentation.base.BaseActivity
import com.android.starzplay.presentation.ui.screens.StarzPlayApp
import com.android.starzplay.utils.localehelper.LanguageManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var languageManager: LanguageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val coroutineScope = rememberCoroutineScope()
            val lang by languageManager.languageFlow.collectAsState(initial = LOCALE_EN)
            CompositionLocalProvider(
                LocalLayoutDirection provides if (lang == LOCALE_AR) LayoutDirection.Rtl else LayoutDirection.Ltr
            ) {
                StarzPlayApp(languageManager, coroutineScope)
            }
        }
    }
}


