package kr.loner.willog.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.loner.willog.domain.SearchPhotosUserCase
import kr.loner.willog.model.Photo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchPhotosUserCase: SearchPhotosUserCase
) : ViewModel() {

    val dlist: MutableStateFlow<PagingData<Photo>> = MutableStateFlow(PagingData.empty())
    val query: MutableStateFlow<String> = MutableStateFlow("a")
    val prevQuery: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch {
            launch {
                searchPhotosUserCase(query.value, prevQuery.value).collectLatest { pagingData ->
                    prevQuery.value = query.value
                    dlist.value = pagingData
                }
            }

            launch {
                query.collect{
                    searchPhotosUserCase(it,prevQuery.value).collect{ list ->
                        prevQuery.value = it
                        dlist.value = list
                    }
                }
            }
        }
    }

    fun asd(text:String){
        query.value = text
    }
}