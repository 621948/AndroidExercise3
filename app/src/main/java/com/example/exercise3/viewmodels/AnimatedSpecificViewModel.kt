package com.example.exercise3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise3.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AnimatedSpecificUiState(
    val backToHomeText: Int = R.string.back_to_home
)

@HiltViewModel
class AnimatedSpecificViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(AnimatedSpecificUiState()) // StateFlow for states
    val uiState = _uiState.asStateFlow()

    private var _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome = _navigateToHome.asSharedFlow()

    fun navigateToHome() {
        viewModelScope.launch {
            _navigateToHome.emit(Unit)
        }
    }

    fun onHomeButtonClicked() {
        navigateToHome()
    }
}