package com.android.starzplay.presentation.ui.screens.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.starzplay.R
import com.android.starzplay.presentation.base.BaseActivity.Companion.LOCALE_AR
import com.android.starzplay.presentation.base.BaseActivity.Companion.LOCALE_EN
import com.android.starzplay.presentation.components.CommonText
import com.android.starzplay.utils.localehelper.LanguageManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageScreen(
    languageManager: LanguageManager,
    coroutineScope: CoroutineScope,
    onBackClick: () -> Unit,
    onLanguageSelected: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { CommonText(stringResource(R.string.language), textSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CommonText(
                value = stringResource(R.string.select_a_language),
                textStyle = MaterialTheme.typography.titleLarge,
                textSize = 22.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(onClick = {
                    coroutineScope.launch {
                        languageManager.setLanguage(LOCALE_EN)
                        onLanguageSelected()
                    }
                }) {
                    CommonText(stringResource(R.string.english), textColor = Color.White)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(onClick = {
                    coroutineScope.launch {
                        languageManager.setLanguage(LOCALE_AR)
                        onLanguageSelected()
                    }
                }) {
                    CommonText(stringResource(R.string.arabic), textColor = Color.White)
                }
            }
        }
    }
}