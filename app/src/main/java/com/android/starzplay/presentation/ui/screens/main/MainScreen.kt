package com.android.starzplay.presentation.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.utils.localehelper.LanguageManager

@Composable
fun MainScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    languageManager: LanguageManager,
    onItemClick: (MovieDomainDetails) -> Unit,
    onLanguageClick: () -> Unit,
) {

    val searchParams by viewModel.params.collectAsStateWithLifecycle()
    val state by viewModel.searchState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
    ) {

        MainScreenTopBar(onLanguageClick)

        HorizontalDivider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        MainScreenSearchBar(searchParams, viewModel, languageManager)

        Spacer(modifier = Modifier.height(16.dp))

        MoviesContent(state, onItemClick)
    }
}












