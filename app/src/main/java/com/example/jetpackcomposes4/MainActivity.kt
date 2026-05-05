package com.example.jetpackcomposes4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposes4.ui.theme.JetpackComposeS4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeS4Theme {

                Column {
                    Text()
                    LearnColumn()
                    LearnRow()
                }
            }
        }
    }
}

@Composable
fun Text(){
    Text(
        text = "JetPack Compose",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth())
}
@Composable
fun LearnColumn(){

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Learn Column")
        Button(
            onClick = {}) {
            Text("Click Me 1")
        }

        Button(
            onClick = {}) {
            Text("Click Me 2")
        }
    }

}

@Composable
fun LearnRow() {
    Text("Learn Rows", textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth())
    Row(
        modifier = Modifier
            .padding(top = 1.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    )
    {
        Button(
            onClick = {}) {
            Text("Click Me 1")
        }

        Button(
            onClick = {}) {
            Text("Click Me 2")
        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackComposeS4Theme {
        Column {
            Text()
            LearnColumn()
            LearnRow()
        }
    }
}