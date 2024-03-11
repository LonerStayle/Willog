package kr.loner.willog.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.loner.willog.domain.BookmarkToggleUseCase
import kr.loner.willog.domain.GetBookmarkedPhotosUseCase
import kr.loner.willog.domain.SearchPhotosUserCase
import kr.loner.willog.model.BookmarkedPhoto
import kr.loner.willog.model.Photo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchPhotosUserCase: SearchPhotosUserCase,
    private val getBookmarkedPhotosUseCase: GetBookmarkedPhotosUseCase,
    private val bookmarkedToggleUseCase: BookmarkToggleUseCase
) : ViewModel() {


    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _queryFlow = MutableStateFlow("")
    private val _cacheValues = MutableStateFlow(PhotoListCacheValues())


    @OptIn(ExperimentalCoroutinesApi::class)
    val searchPhotos: StateFlow<PagingData<Photo>> = _queryFlow
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
                searchPhotos
            }
        }.catch { e ->
            e.printStackTrace()
            _errorFlow.emit(e)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            PagingData.empty()
        )

    val bookmarkedPhotos = getBookmarkedPhotosUseCase()

    fun setQuery(query: String) {
        _queryFlow.value = query
    }

    fun bookmarkToggle(photo: Photo) {
        viewModelScope.launch {
            bookmarkedToggleUseCase(
                BookmarkedPhoto(
                    id = photo.id,
                    thumb = photo.urls.thumb
                )
            )
        }

    }

    data class PhotoListCacheValues(
        val prevQuery: String? = null,
        val cacheSearchPhotos: Flow<PagingData<Photo>>? = null,
    )
}