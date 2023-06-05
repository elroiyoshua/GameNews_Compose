package com.example.gamenews_compose.ui.theme.screen.detail
import com.example.gamenews_compose.R
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gamenews_compose.ViewModelFactory
import com.example.gamenews_compose.di.DependencyInjection
import com.example.gamenews_compose.ui.theme.GameNews_ComposeTheme
import com.example.gamenews_compose.ui.theme.common.UiState
import com.example.gamenews_compose.ui.theme.screen.profile.ProfileScreen

@Composable
fun DetailScreen(
    gamesId: Long,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(
            DependencyInjection.provideRepository()
        )
    ),
    navigateBack : () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{
        when(it){
            is UiState.Loading -> {viewModel.getGamesById(gamesId)}
            is UiState.Success ->{
                val data = it.data
                DetailContent(name = data.games.name, dev = data.games.dev, price =data.games.price , photo =data.games.photo , desc = data.games.desc, rate = data.games.rate )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    name:String,
    dev:String,
    price:String,
    @DrawableRes photo: Int,
    desc: String,
    rate: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Image(
            painter = painterResource(photo),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Text(
            text = "PRICE: $price",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "RATING: $rate",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = "DEVELOPER: $dev",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = desc,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailPreview() {
    GameNews_ComposeTheme {
        DetailContent("Persona 4 Golden","Atlus", "Rp. 299.999", R.drawable.logo1,
            "story of a star blitzball player, Tidus, who journeys with a young and beautiful summoner named Yuna on her quest to save the world of Spira from an endless cycle of destruction wrought by the colossal menace Sin.", "92")
    }
}