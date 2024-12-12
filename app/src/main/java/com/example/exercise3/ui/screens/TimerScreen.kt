package com.example.exercise3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.exercise3.R
import com.example.exercise3.viewmodels.TimerViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TimerScreen(viewModel: TimerViewModel = hiltViewModel(), onNavigateToHome: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigateToHome.collectLatest { onNavigateToHome() }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = state.seconds.toString())

        Button(
            onClick = viewModel::onTimerButtonClicked
        ) {
            Text(text = stringResource(state.timerButtonText))
        }

        Button(
            onClick = viewModel::onHomeButtonClicked
        ) {
            Text(text = stringResource(R.string.back_to_home))
        }
    }
}