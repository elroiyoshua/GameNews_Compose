package com.example.gamenews_compose.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamenews_compose.R
import com.example.gamenews_compose.ui.theme.GameNews_ComposeTheme
import com.example.gamenews_compose.ui.theme.Shapes

@Composable
fun GamesItem(
    photo: Int,
    name: String,
    dev: String,
    price: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(photo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp)
                .clip(Shapes.medium)
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = dev,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun GamesItemPreview() {
    GameNews_ComposeTheme {
        GamesItem(photo = R.drawable.logo1, name ="Dota2" , dev = "Valve", price = "Free")
    }
}