package kr.loner.feature.photo.search

import android.util.Log
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.loner.willog.domain.GetBookmarkedPhotosUseCase
import kr.loner.willog.domain.SearchPhotosUserCase
import kr.loner.willog.model.Photo
import javax.inject.Inject

@HiltViewModel
class PhotoSearchViewModel @Inject constructor(
    private val searchPhotosUserCase: SearchPhotosUserCase,
    getBookmarkedPhotosUseCase: GetBookmarkedPhotosUseCase,
) : ViewModel() {
    private val _photoSearchUiEffect = MutableStateFlow<PhotoSearchEffect>(PhotoSearchEffect.Ide)
    val photoSearchUiEffect: StateFlow<PhotoSearchEffect> = _photoSearchUiEffect

    private val _queryFlow = MutableStateFlow("")
    private val _cacheValues = MutableStateFlow(PhotoSearchCacheValues())

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchPhotos: Flow<PagingData<Photo>> = _queryFlow
        .filter(String::isNotEmpty)
        .flatMapLatest { query ->
            val cacheValue = _cacheValues.value
            val searchPhotos = searchPhotosUserCase(query).cachedIn(viewModelScope)

            if (cacheValue.prevQuery == query) {
                _cacheValues.update { it.copy(prevQuery = query) }
                cacheValue.cacheSearchPhotos ?: emptyFlow()
            } else {
                _cacheValues.update { cache ->
                    cache.copy(
                        prevQuery = query,
                        cacheSearchPhotos = searchPhotos
                    )
                }
                searchPhotosUserCase(query).cachedIn(viewModelScope)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            PagingData.empty()
        )

    private val bookmarkedPhotos = getBookmarkedPhotosUseCase()

    init {
        viewModelScope.launch {
            bookmarkedPhotos.collect {
                refresh()
            }
        }

    }

    fun setQuery(query: String) {
        _queryFlow.value = query
    }

    private fun refresh() {
        viewModelScope.launch {
            _photoSearchUiEffect.value = PhotoSearchEffect.RefreshPaging
        }
    }

    data class PhotoSearchCacheValues(
        val prevQuery: String? = null,
        val cacheSearchPhotos: Flow<PagingData<Photo>>? = null,
    )
}