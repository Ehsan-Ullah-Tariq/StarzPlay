package com.android.starzplay.presentation.ui.screens.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.android.starzplay.R
import com.android.starzplay.presentation.model.SearchParams
import com.android.starzplay.utils.localehelper.LanguageManager

@Composable
fun MainScreenSearchBar(
    searchParams: SearchParams,
    viewModel: SearchViewModel,
    languageManager: LanguageManager
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val lang by languageManager.languageFlow.collectAsState(initial = "en")

    OutlinedTextField(
        value = searchParams.query,
        onValueChange = { viewModel.onQueryChanged { copy(query = it.trim(),language = lang) } },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.search_movies)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            viewModel.onQueryChanged { copy(query = query.trim(),language = lang) }
        })
    )
}