package com.example.jetpackcomposes4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposes4.ui.theme.JetpackComposeS4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeS4Theme {

                Column {
                    Text()
                    LearnColumn()
                    LearnRow()
                    LearnBox()
                }
            }
        }
    }
}

@Composable
fun Text(){
    Text(
        text = "Learn JetPack Compose",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(10.dp)
            .padding(top = 20.dp)
            .fillMaxWidth())
}
@Composable
fun LearnColumn(){

    Column(
        modifier = Modifier
            .padding(10.dp)
            .padding(top = 10.dp)
            .border(1.dp, color = Color.Black)
            .fillMaxWidth(),
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
    Column(modifier = Modifier
        .padding(10.dp)
        .border(1.dp, color = Color.Black)

    ) {
        Text(
            "Learn Rows", textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
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
}

@Composable
fun LearnBox() {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, color = Color.Black)

    ) {
    Text("Learn Box's", textAlign = TextAlign.Center,
        modifier = Modifier

            .fillMaxWidth())
    Row(
        modifier = Modifier
            .padding(5.dp)
            .border(1.dp, color = Color.Black)

            .fillMaxWidth(),
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Cyan)

                .size(100.dp)
        ){
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.Yellow)
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Box1")
            }
        }


        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Cyan)

                .size(100.dp)
        ){
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.Yellow)
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Box2")
            }
        }


        Box(
            modifier = Modifier
                .padding(10.dp)
                .background(color = Color.Cyan)

                .size(100.dp)
        ){
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.Yellow)
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ){
                Text("Box3")
            }
        }
    }}
}







@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackComposeS4Theme {
        Column {
            Text()
            LearnColumn()
            LearnRow()
            LearnBox()
        }
    }
}