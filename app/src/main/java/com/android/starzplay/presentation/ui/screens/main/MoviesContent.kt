package com.android.starzplay.presentation.ui.screens.main


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.android.starzplay.R
import com.android.starzplay.domain.models.MovieDomainDetails
import com.android.starzplay.domain.models.MoviesDomainResponse
import com.android.starzplay.presentation.components.CommonText
import com.android.starzplay.utils.ResultResource

@Composable
fun MoviesContent(
    result: ResultResource<MoviesDomainResponse>,
    onItemClick: (MovieDomainDetails) -> Unit,
) {
    when (result) {
        is ResultResource.Loading -> {
            CircularProgressIndicator()
        }

        is ResultResource.Error -> {
            Text(
                text = result.message.toString(),
                color = MaterialTheme.colorScheme.error
            )
        }

        is ResultResource.Success -> {
            val movies = result.value.movies
                .orEmpty()

            if (movies.isEmpty()) {
                Text(stringResource(R.string.no_results_found), modifier = Modifier.padding(8.dp))

            } else {
                val groupedList = movies.groupBy { it.movieCategory.orEmpty() }.toList()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(groupedList) { (type, list) ->

                        CommonText(
                            value = type.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase() else it.toString()
                            },
                            textStyle = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        ) {
                            items(
                                items = list,
                                key = { movie -> movie.id ?: movie.hashCode() }
                            ) { movie ->
                                MovieCard(movie) { movie ->
                                    onItemClick(movie)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: MovieDomainDetails, onItemClick: (MovieDomainDetails) -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp)
            .clickable {
                onItemClick(movie)
            }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CommonText(value = stringResource(R.string.image), textStyle = MaterialTheme.typography.titleSmall)
            }

            Spacer(modifier = Modifier.height(8.dp))

            CommonText(
                value = movie.title.orEmpty(),
                textStyle = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}