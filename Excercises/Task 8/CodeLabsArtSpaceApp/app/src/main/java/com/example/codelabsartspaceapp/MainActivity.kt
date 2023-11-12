package com.example.codelabsartspaceapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {

    private val artworks = listOf(
        "Haku the white long dragon flying in the fantasy night sky, chihiro riding his back",
        "Castle in moonlit night",
        "Gerald of Rivia on a battlefield with harpies flying in the sky",
        "Gerald of Rivia fighting a griffin flying in the background by casting a fire-spell",
        "Gerald of Rivia training to cast a fire-spell")
    private val artworksDescription = listOf(
        "2022, DALLE 2",
        "2021, DALLE 2",
        "2022, DALLE 2",
        "2022, DALLE 2",
        "2022, DALLE 2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ArtSpaceApp()
            }
        }
    }

    @Preview
    @Composable
    fun ArtSpaceApp(){
        var currentIndex by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = getArtworkResourceId(currentIndex)),
                contentDescription = "Artwork",
                modifier = Modifier
                    .fillMaxWidth()
                    //.wrapContentSize(Alignment.Center)
            )

            Spacer(modifier = Modifier.height(128.dp))

            Text(
                text = "Artwork: ${artworks[currentIndex]}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Date of creation and tool ${artworksDescription[currentIndex]}",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                        }else {
                            currentIndex = artworks.size - 1
                        }
                    }
                ){
                    Text("Backward")
                }
                Button(onClick = {
                        if (currentIndex < artworks.size - 1) {
                            currentIndex++
                        }else{
                            currentIndex = 0
                        }
                    }
                ) {
                    Text("Forward")
                }
            }
        }
    }

    @Composable
    fun getArtworkResourceId(index: Int): Int {
        return when (index) {
            0 -> R.drawable.artwork_0
            1 -> R.drawable.artwork_1
            2 -> R.drawable.artwork_2
            3 -> R.drawable.artwork_3
            else -> R.drawable.artwork_4
        }
    }
}
