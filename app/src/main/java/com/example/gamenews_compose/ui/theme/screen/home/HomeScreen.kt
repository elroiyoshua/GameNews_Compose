package com.example.gamenews_compose.ui.theme.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamenews_compose.ViewModelFactory
import com.example.gamenews_compose.di.DependencyInjection
import com.example.gamenews_compose.model.OrderGames
import com.example.gamenews_compose.ui.theme.GameNews_ComposeTheme
import com.example.gamenews_compose.ui.theme.common.UiState
import com.example.gamenews_compose.ui.theme.components.GamesItem
import com.example.gamenews_compose.ui.theme.screen.profile.ProfileScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(DependencyInjection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Error -> {
            }
            is UiState.Success -> {
                HomeContent(
                    orderGames = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    orderGames: List<OrderGames>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(320.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
    ){
        items(orderGames){
            GamesItem(photo = it.games.photo, name = it.games.name , dev =it.games.dev , price = it.games.price,modifier = Modifier.clickable {
                navigateToDetail(it.games.id)
            } )
        }
    }
}

