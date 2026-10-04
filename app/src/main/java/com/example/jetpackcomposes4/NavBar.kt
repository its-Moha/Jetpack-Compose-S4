package com.example.jetpackcomposes4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(5.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("Header", fontSize = 18.sp)
    }
}

data class NavMenus(
    val title:String,
    val icon: ImageVector,
    val disc: String,
    val route: String
)

val navBarItems = listOf(
    NavMenus(
        title = "Ui",
        icon = Icons.Default.Dashboard,
        disc = "Ui",
        route = Screens.Ui.route
    ),

    NavMenus(
        title = "Android",
        icon = Icons.Default.Android,
        disc = "Android",
        route = Screens.Android.route
    ),

    NavMenus(
        title = "Topics",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        disc = "Topics",
        route = Screens.Topics.route
    ),

    NavMenus(
        title = "Data",
        icon = Icons.Default.SdStorage,
        disc = "Data",
        route = Screens.Data.route
    ),

)

@Composable
fun DrawerBody(
    onItemNavClick: (NavMenus) -> Unit
) {

    LazyColumn{
        items(navBarItems){item ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { (onItemNavClick(item)) }
            ) {

                Icon(item.icon,item.disc)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(item.title, fontSize = 18.sp)
            }

        }
    }

}