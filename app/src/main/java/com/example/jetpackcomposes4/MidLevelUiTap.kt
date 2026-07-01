package com.example.jetpackcomposes4

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size


@Composable
fun LoadImage() {

    val imageUrl = "https://mrwallpaper.com/images/high/1080p-hd-cottage-by-the-lake-ozfn18uzcfe0h7oq.webp"

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround)

    {

        Text(
            text = "Load Image From Internet",
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center
        )


        val model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .size { Size.ORIGINAL }
            .build()

//        AsyncImage(
//            modifier = Modifier
//                .clip(RoundedCornerShape(10.dp)),
//            model = model,
//            contentDescription = "image",
//
//            )
//
//        Spacer(modifier = Modifier.padding(10.dp))

        val painter = rememberAsyncImagePainter(model = model)

        val imageState by painter.state.collectAsState()

        when(imageState){

            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    painter = painter,
                    contentDescription = "image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),

                    )
            }

            else -> {
                Icon(
                    modifier = Modifier
                        .size(100.dp),

                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = "image not supported",
                    tint = MaterialTheme.colorScheme.error
                )

            }
        }


    }

    }


@Composable
fun Dialog() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(top = 10.dp)
            .padding(10.dp)
            .border(1.dp, color = Color.Black)
        ,

    ) {

        Text(
            text = "Dialog In Compose",
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center
        )

        var openDialog by remember {
            mutableStateOf(false)
        }

        var myTextField by remember {
            mutableStateOf("")
        }

        var addedText by remember {
            mutableStateOf("")
        }

        val focusManager = LocalFocusManager.current

        val context =  LocalContext.current

        Button(
            onClick = {
                openDialog = true
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Open dialog")
        }

        if (openDialog){
            AlertDialog(


                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "add",
                    )
                },

                title = {
                    Text("Add Item")
                },

                text = {
                    OutlinedTextField(
                        value = myTextField,
                        onValueChange = {
                            myTextField = it
                        },
                        placeholder = {
                            Text("Add Item")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "add"
                            )
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
                        shape = RoundedCornerShape(10.dp)

                    )
                },

                onDismissRequest = {
                    openDialog = false
                },


                confirmButton = {

                    TextButton(
                        onClick = {

                            if (myTextField.isNotEmpty()) {
                                Toast.makeText(context, "Item added $myTextField", Toast.LENGTH_SHORT).show()

                                addedText = myTextField
                                openDialog = false
                                myTextField = ""
                            }else{
                                Toast.makeText(
                                    context,
                                    "Please enter text",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text("Confirm")
                    }
                },

                dismissButton = {
                    TextButton(
                        onClick = {
                            myTextField = ""
                            openDialog = false
                            focusManager.clearFocus()
                        }
                    ) {
                        Text("Cancel")
                    }
                }

            )


        }

        Spacer(modifier = Modifier.padding(4.dp))
        if (addedText.isNotEmpty()){
            Text(
                text = "Item added = $addedText",
                modifier = Modifier.padding(10.dp))
        }


    }
}


data class Items(
    val isChecked: Boolean,
    val task: String
)

var myTodoItems = listOf(
    Items(true, "Learn Kotlin"),
    Items(false, "Learn Compose"),
    Items(false, "Learn Retrofit")
)

@Composable
fun TodoListCheckbox() {

    var todoList by remember {
        mutableStateOf(myTodoItems)
    }

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(230.dp)
            .padding(10.dp)
            .border(1.dp, color = Color.Black)
    ) {
        Text("Todo List Checkbox", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())


        //For each to-do item in my list, do something.
        todoList.forEachIndexed { index, items ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(2.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp )

            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Checkbox(
                        checked = items.isChecked,
                        onCheckedChange = {isChecked ->

                            //Make a new copy of my list that I’m allowed to edit.
                            //.also { ... }
                            //This says, “While I’m making that new list,
                            // also do something with it inside these curly braces.”
                            todoList = todoList.toMutableList().also {
                                it[index] = it[index].copy(isChecked = isChecked)
                                ///Find the box at the tapped position (index) and make a new version of it with the checkmark flipped.
                                //
                                //it[index] means “the box in the list at this position.”
                                //
                                //.copy(isChecked = isChecked) means “make a new box with the same name, but update the checkmark.”
                            }
                        }
                    )
                    Text(text = items.task)
                }

            }
        }

    }
}


@Composable
fun Chips(
    chips: List<String>
) {

    var selectedChip by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .height(120.dp)
            .border(1.dp, Color.DarkGray)

    ) {

        Text("Chips", textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())

        LazyRow {
            items(chips.size) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = {
                                selectedChip = it
                            }
                        )
                        .background(
                            if (selectedChip == it) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.outline
                        )
                        .padding(10.dp)


                ) {
                    Text(chips[it], color = Color.White)
                }
            }
        }
    }

}











