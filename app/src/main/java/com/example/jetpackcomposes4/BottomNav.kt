package com.example.jetpackcomposes4

import android.app.FragmentManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.material.icons.filled.Topic
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class Screens( val route: String){

    data object Ui: Screens("Ui_Screen")
    data object Android: Screens("Android_Screen")
    data object Topics: Screens("Topics_Screen")
    data object Data: Screens("Data_Screen")
    object LazyRowScreen:Screens("lazyRowScreen")
    object ColumnRowScreen:Screens("columnRowScreen")
    object GridRowScreen:Screens("gridRowScreen")

}


data class Navigation(
    val title: String,
    val icon: ImageVector,
    val disc: String,
    val route: String
)

val navigationItems = listOf(
    Navigation(
        title = "Ui",
        icon = Icons.Default.Dashboard,
        disc = "Ui",
        route = Screens.Ui.route
    ),

    Navigation(
        title = "Android",
        icon = Icons.Default.Android,
        disc = "Android",
        route = Screens.Android.route
    ),

    Navigation(
        title = "Topics",
        icon = Icons.AutoMirrored.Filled.MenuBook,
        disc = "Topics",
        route = Screens.Topics.route
    ),

    Navigation(
        title = "Data",
        icon = Icons.Default.SdStorage,
        disc = "Data",
        route = Screens.Data.route
    ),
)


@Composable
fun UiScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column() {
            Text()
            MyTabs()
        }
    }
}


@Composable
fun AndroidScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Android Screen")
    }
}
@Composable
fun TopicsScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Topics Screen")
    }
}

@Composable
fun DataScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Data Screen")
    }
}


@Composable
fun BottomNavigationBar(
    navController: NavController
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 5.dp
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentRoute,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(item.title)
                },
                icon = {
                    Icon(item.icon,item.disc)
                },

                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.outline,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}


