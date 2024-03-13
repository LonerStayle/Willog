package kr.loner.willog.feature.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _appBarText = MutableStateFlow("")
    val appBarText: StateFlow<String> get() = _appBarText

    fun updateAppBarText(text: String) {
        _appBarText.value = text
    }
}