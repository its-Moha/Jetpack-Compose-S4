package com.example.jetpackcomposes4

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size


sealed class Screens(val route:String){
    object HomeScreen:Screens("homeScreen")
    object LazyRowScreen:Screens("lazyRowScreen")
    object ColumnRowScreen:Screens("columnRowScreen")
    object GridRowScreen:Screens("gridRowScreen")
}


@Composable
fun ListNavigation() {


      // the driver -> "Go to another screen."
    val navController = rememberNavController()

    // This is the map -> If someone navigates to 'lazyRowScreen', show LazyListScreen().
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }


        composable(Screens.LazyRowScreen.route) {
            LazyListScreen()
        }

        composable(Screens.ColumnRowScreen.route) {
            ColumnListScreen()
        }

        composable(Screens.GridRowScreen.route) {
            GridListScreen()
        }
    }
}



data class ListItems(
    val name : String,
    val image:Int
)

val myListItems = listOf(
    ListItems("beef",R.drawable.beef),
    ListItems("burger",R.drawable.burger),
    ListItems("chicken",R.drawable.chicken),
    ListItems("iceCream",R.drawable.icecream),
    ListItems("liver",R.drawable.liver),
    ListItems("macaroni",R.drawable.macaroni),
    ListItems("noodles",R.drawable.noodles),
    ListItems("omelette",R.drawable.omelette),
    ListItems("pizza",R.drawable.pizza),
    ListItems("rice",R.drawable.rice),
    ListItems("sandwich",R.drawable.sandwich),
    ListItems("sushi",R.drawable.sushi),
)


// what one item Will Look like

@Composable
fun MyItem(

    myAllList: ListItems
) {

    Card(
        modifier = Modifier.padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Image(
                painter = painterResource(id = myAllList.image),
                contentDescription = "Image",
                modifier = Modifier
                    .height(160.dp)
                    .width(160.dp),
                contentScale = ContentScale.Crop,
            )

            Text(
                text = myAllList.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center

            )
        }
    }

}

@Composable
fun LazyListScreen() {

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxSize()
        .padding(7.dp)
        .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        LazyRow {
            items(myListItems) { item ->
                MyItem(myAllList = item)
            }
        }
    }
}

@Composable
fun ColumnListScreen() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(7.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(myListItems) { item ->

                MyItem(myAllList = item)
            }
        }
    }
}

//columns = GridCells.Fixed(2),
//            contentPadding = PaddingValues(10.dp),
//            verticalArrangement = Arrangement.spacedBy(10.dp),
//            horizontalArrangement = Arrangement.spacedBy(13.dp),


@Composable
fun GridListScreen() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(7.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            items(myListItems) { item ->
                MyItem(myAllList = item)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {


    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            .fillMaxWidth()
            .border(1.dp, color = Color.Black)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Types of List in Compose", fontSize = 15.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp), textAlign = TextAlign.Center)
        Button(
            modifier = Modifier
                .width(180.dp),
            onClick = {
                navController.navigate(Screens.LazyRowScreen.route)
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
            ) {
            Text("Lazy Row Screen")
        }


        Button(
            modifier = Modifier
                .width(180.dp),
            onClick = {
                navController.navigate(Screens.ColumnRowScreen.route)
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text("Column Row Screen")
        }


        Button(
            modifier = Modifier
                .width(180.dp),
            onClick = {
                navController.navigate(Screens.GridRowScreen.route)
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text("Grid Row Screen")
        }
    }

}







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

    // remember : Keep this value while the screen is alive
    // mutableStateOf = “If this value changes, redraw the screen.”
    // todoList is the list that Compose watches

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
        // index → its position (0, 1, 2…)
        // items → the actual todoitem

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

                        // When the user taps the checkbox, this function runs.
                        onCheckedChange = {isChecked ->

                            //Make a new copy of my list that I’m allowed to edit.
                            // b/c our original list is read-only. So we create a new list that can be edited.
                            //.also { ... }
                            //This says, “While I’m making that new list,
                            // also do something with it inside these curly braces.”
                            todoList = todoList.toMutableList().also {
                                it[index] = it[index].copy(isChecked = isChecked)
                                ///Find the box at the tapped position (index) and make a new version of it with the checkmark flipped.
                                //
                                //it[index] means “the box in the list at this position.” like: “Get the item at position 1.”
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

    //The function receives a list of strings.
    //For example:
    //Chips( chips = listOf("Kotlin","Compose", "Retrofit", "Firebase"))
   // so instead of listing them down now we will do it letter
    chips: List<String>
   // whenever you call Chips(), you must give it a list of strings.
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
            //This repeats something for every chip.


            items(chips.size) { index ->

                //Each chip is drawn inside a Box.

                //Box as a container.
                //One Box = One chip.
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = {
                                selectedChip = index
                            }
                        )
                        .background(
                            if (selectedChip == index)
                                MaterialTheme.colorScheme.primaryContainer
                            else
                                MaterialTheme.colorScheme.outline
                        )
                        .padding(10.dp)


                ) {
                    //If index = 0  it displays  Kotlin
                    //If index = 1  it displays Compose
                    //and so on.
                    Text(chips[index], color = Color.White)
                }
            }
        }
    }

}














