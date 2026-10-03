package com.example.jetpackcomposes4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

@Composable
fun Header() {

    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        contentAlignment = Alignment.Center
    ) {
        Text("Header", fontSize = 17.sp)
    }

}

data class MyMenuItem(
    var title: String,
    var icon: ImageVector,
    val disc: String,
    val route: String
)

val myItems = listOf(
    MyMenuItem(
        title = "Ui",
        icon = Icons.Default.Dashboard,
        disc = "Ui",
        route = Screens.Ui.route
    ),

    MyMenuItem(
        title = "Android",
        icon = Icons.Default.Android,
        disc = "Android",
        route = Screens.Android.route
    ),


    MyMenuItem(
        title = "Topics",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        disc = "Topics",
        route = Screens.Topics.route
    ),

    MyMenuItem(
        title = "Data",
        icon = Icons.Default.SdStorage,
        disc = "Data",
        route = Screens.Data.route
    ),

)



@Composable
fun DrawerBody(
    onItemClick : (MyMenuItem) -> Unit
) {


    LazyColumn(
        modifier = Modifier
            .padding(5.dp)

    ) {items(myItems) { item ->
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable{onItemClick(item)}
        ) {

                Icon(item.icon, item.disc)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(item.title, fontSize = 20.sp)
            }
        }

}
}