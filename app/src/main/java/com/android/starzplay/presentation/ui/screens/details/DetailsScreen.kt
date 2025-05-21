package com.android.starzplay.presentation.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.starzplay.R
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.presentation.components.CommonText
import com.android.starzplay.ui.theme.StarzPlayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    item: MovieDomainDetails,
    onPlayClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { CommonText(stringResource(R.string.movie_details)) },
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
                .systemBarsPadding()
                .padding(paddingValues)
        ) {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            DetailsScreenContent(item = item) { url ->
                onPlayClick(url)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    StarzPlayTheme {
        DetailsScreen(
            item = MovieDomainDetails(
                movieCategory = "TV"
            ), onPlayClick = {}, onBackClick = {}
        )
    }
}