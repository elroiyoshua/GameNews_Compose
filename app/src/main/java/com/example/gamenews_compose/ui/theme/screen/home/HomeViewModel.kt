package com.example.gamenews_compose.ui.theme.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamenews_compose.model.OrderGames
import com.example.gamenews_compose.repository.GamesRepository
import com.example.gamenews_compose.ui.theme.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: GamesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderGames>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderGames>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllGames()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderGames ->
                    _uiState.value = UiState.Success(orderGames)
                }
        }
    }
}