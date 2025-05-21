package com.android.starzplay.presentation.ui.screens.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.starzplay.data.remote.dto.MoviesSearchResultResponse
import com.android.starzplay.domain.models.MoviesDomainResponse
import com.android.starzplay.domain.usecase.SearchUseCase
import com.android.starzplay.presentation.model.SearchParams
import com.android.starzplay.utils.ResultResource
import com.android.starzplay.utils.SEARCH_PARAMS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _params: MutableStateFlow<SearchParams> = MutableStateFlow(savedStateHandle["searchParams"] ?: SearchParams())
    val params: StateFlow<SearchParams> = _params


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchState: StateFlow<ResultResource<MoviesDomainResponse>> = _params
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { params ->
            if (params.query.isBlank()) {
                flowOf(ResultResource.Success(
                    MoviesDomainResponse(
                        movies = emptyList(),
                    )
                ))
            } else {
                searchUseCase(
                    query = params.query,
                    includeAdult = params.includeAdult,
                    language = params.language,
                    page = params.page
                ).onStart { emit(ResultResource.Loading) }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ResultResource.Loading
        )

    fun onQueryChanged(update: SearchParams.() -> SearchParams) {
        val newParams = _params.value.update()
        _params.value = newParams
        savedStateHandle["searchParams"] = newParams
    }
}
