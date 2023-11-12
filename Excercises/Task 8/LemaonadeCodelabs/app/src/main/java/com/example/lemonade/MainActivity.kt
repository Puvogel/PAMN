package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    Lemonade(name = "Lemonade")
}

@Composable
fun Lemonade(name: String, modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    var clicksToSqueeze by remember { mutableStateOf((2..4).random()) }
    var clickCounter by remember { mutableStateOf(0) }
    var cycleState by remember { mutableStateOf(1) }

    val imageResource = when (cycleState) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (cycleState) {
        1 -> "Text 1"
        2 -> "Text 2"
        3 -> "Text 3"
        else -> "Text 4"
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(imageResource),
            contentDescription = "Lemonade lifecycle image",
            modifier.clickable {
                if (cycleState < 4) {
                    if (cycleState == 2 && clickCounter < clicksToSqueeze){
                        clickCounter++
                    }else {
                        cycleState++
                    }
                }else {
                    cycleState = 1
                    clicksToSqueeze = (2..4).random()
                    clickCounter = 0
                }
            })
        Text(text = textResource)
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}