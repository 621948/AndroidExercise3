package com.example.exercise3.viewmodels

import com.example.exercise3.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TimerUiState(
    val seconds: Int = 0,
    val timerButtonText: Int = R.string.timer_button_start
)

@HiltViewModel
class TimerViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TimerUiState()) // StateFlow for states
    val uiState = _uiState.asStateFlow()

    private var _navigateToHome = MutableSharedFlow<Unit>()
    val navigateToHome = _navigateToHome.asSharedFlow()

    private var timerActive: Boolean = false

    fun onTimerButtonClicked() {
        if (timerActive) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    fun onHomeButtonClicked() {
        if (timerActive) {
            stopTimer()
            }

            navigateToHome()
    }

    fun navigateToHome() {
        viewModelScope.launch {
            _navigateToHome.emit(Unit)
        }
    }

    private fun startTimer() {
        _uiState.update { state ->
            state.copy(
                seconds = 0, // Reset seconds
                timerButtonText = R.string.timer_button_stop // Change button text
            )
        }
        timerActive = true

        viewModelScope.launch {
            while (timerActive) {
                delay(timeMillis = 1000) // Delay 1 second
                _uiState.update { state ->
                    state.copy(seconds = state.seconds + 1) // Increment seconds
                }
            }
        }
    }

    private fun stopTimer() {
        timerActive = false
        _uiState.update { state ->
            state.copy(
                timerButtonText = R.string.timer_button_start // Change button text
            )
        }
    }
}