package com.example.exercise3.viewmodels

import android.util.Log
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

data class HomeUiState(
    val messageText: Int = R.string.home_welcome_text,
    val timerText: Int = R.string.home_timer_button,
    val animatedSpecificText: Int = R.string.home_animated_specific_button,
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState()) // StateFlow for states
    val uiState = _uiState.asStateFlow()

    private var _navigateToTimer = MutableSharedFlow<Unit>() // SharedFlow for events
    val navigateToTimer = _navigateToTimer.asSharedFlow()

    private var _navigateToAnimatedSpecific = MutableSharedFlow<Unit>()
    val navigateToAnimatedSpecific = _navigateToAnimatedSpecific.asSharedFlow()


    fun navigateToTimer() {
        viewModelScope.launch {
            Log.d("HomeViewModel", "Emitting navigateToTimer event")
            _navigateToTimer.emit(Unit)
        }
    }

    fun navigateToAnimatedSpecific() {
        viewModelScope.launch {
            Log.d("HomeViewModel", "Emitting navigateToAnimatedSpecific event")
            _navigateToAnimatedSpecific.emit(Unit)
        }
    }
}