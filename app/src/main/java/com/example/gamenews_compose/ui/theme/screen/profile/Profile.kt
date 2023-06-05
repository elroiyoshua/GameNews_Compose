package com.example.gamenews_compose.ui.theme.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamenews_compose.R
import com.example.gamenews_compose.ui.theme.GameNews_ComposeTheme
import com.example.gamenews_compose.ui.theme.components.GamesItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.profil),
                contentDescription = stringResource(id = R.string.about_page),
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.profile_name),
                style = MaterialTheme.typography.h5,

            )
            Text(
                text = stringResource(id = R.string.profile_job),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary
            )
            Text(
                text = stringResource(id = R.string.profile_email),
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun GamesItemPreview() {
    GameNews_ComposeTheme {
        ProfileScreen()
    }
}