package com.example.gamenews_compose.ui.theme.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamenews_compose.model.OrderGames
import com.example.gamenews_compose.repository.GamesRepository
import com.example.gamenews_compose.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GamesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderGames>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderGames>>
        get() = _uiState

    fun getGamesById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getGamesById(rewardId))
        }
    }
}