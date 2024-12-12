package com.example.exercise3.ui.screens


import android.util.Log
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
import com.example.exercise3.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onNavigateToTimer: () -> Unit,
    onNavigateToAnimatedSpecific: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("HomeScreen", "LaunchedEffect triggered")
        viewModel.navigateToTimer.collectLatest { Log.d("HomeScreen", "Navigating to Timer")
            onNavigateToTimer() }
        viewModel.navigateToAnimatedSpecific.collectLatest {Log.d("HomeScreen", "Navigating to AniamtedSpecific")
            onNavigateToAnimatedSpecific() }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(state.messageText))

        Button(
            onClick = viewModel::navigateToTimer
        ) {
            Text(text = stringResource(state.timerText))
        }

        Button(
            onClick = viewModel::navigateToAnimatedSpecific
        ) {
            Text(text = stringResource(state.animatedSpecificText))
        }
    }
}