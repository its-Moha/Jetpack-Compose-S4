package com.example.jetpackcomposes4

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Text(){
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("L")
            }
            append("earn")


            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(" J")
            }
            append("etpack")


            withStyle(
                style = SpanStyle(
                    color = Color.Green,
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(" C")
            }
            append("ompose")
        },
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.DarkGray,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(10.dp)
            .padding(top = 20.dp)
            .fillMaxWidth()
    )
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
                .padding(10.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(20.dp))
                .alpha(0.6f) // transparent
                .background(MaterialTheme.colorScheme.primaryContainer),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Button(
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                elevation = ButtonDefaults.elevatedButtonElevation(20.dp),
                onClick = {}) {
                Text("Click Me 1")
            }

            Button(
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                elevation = ButtonDefaults.elevatedButtonElevation(20.dp),
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
            modifier = Modifier.fillMaxWidth())


        Row(
            modifier = Modifier
                .padding(5.dp)
                .border(1.dp, color = Color.Black)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = Color.Cyan)
                    .weight(1f)
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
                    .weight(1f)
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
                    .weight(1f)
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

@Composable
fun Counter() {

    var count by remember { mutableIntStateOf(0) }



    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, color = Color.Black)
            .fillMaxWidth()

    ) {
        Text(
            text = "Counter",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Button(
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    count++
                }) {
                Text("Count")
            }



            Text(
                text = count.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                shape = RoundedCornerShape(15.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
                onClick = {
                    count = 0
                }) {
                Text("Reset")
            }
        }
    }}

@Composable
fun TextState() {

    var textState by remember { mutableStateOf("Hi") }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, color = Color.Black)
            .fillMaxWidth()
    ) {
        Text(
            text =  "Click button to change text state = $textState",
            fontSize = 15.sp,
            modifier = Modifier.offset(10.dp)
        )
        Button(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.offset(10.dp),
            onClick = {
                textState = "Hello there"
            }) {
            Text("Change Text State")
        }
    }
}


@Composable
fun TextVisibilityState(){


    //toggle
    var isVisible by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()

            .padding(10.dp)
            .border(1.dp, Color.Black)
    ) {

        Text(
            text =  "Text Visibility State",
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center)

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {


                    //When you click the button, it runs !isVisible (the "NOT" operator).
                    //If it was true, it becomes false. If it was false, it becomes true.
                    //As soon as this value changes, Compose triggers a Recomposition—it basically re-runs the function to see what the UI should look like now.
                    isVisible = !isVisible
                }
            ) {
                Text(
                    if(isVisible)"Hide Text" else "Show Text")
            }


            Text(modifier = Modifier
                .alpha(if (isVisible) 1f else 0.3f),


                text = if(isVisible)"Text is Visible" else "Text isn't Visible")
        }
    }
}


@Composable
fun TextFieldExample() {

    var textFieldState by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    var showResult by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .padding(10.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    ) {
        Text(
            text =  "Text Field",
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )


        TextField(
            value = textFieldState,
            onValueChange = {textFieldState = it},
            label = {
                Text("TextField")
            },
            placeholder = {
                Text("Enter your text here ...")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black

            ),

            modifier = Modifier
                .fillMaxWidth(),
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    showResult = true
                }
            ) {
                Text("Show Text")
            }

            if (showResult){
                Text("result is = $textFieldState")
            }
        }}
}

@Composable
fun ImageExample() {

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Black)
    ) {
        Text(
            text = "Image",
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(id = R.drawable.me),
            contentDescription = "Me",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .size(200.dp)
                .clip(CircleShape)
            ,
            contentScale = ContentScale.Crop
        )
    }
}
