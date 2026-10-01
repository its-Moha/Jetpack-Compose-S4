package com.example.jetpackcomposes4

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(

    onclick: () -> Unit
) {

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text("TopAppBar")
        },

        navigationIcon = {
            IconButton(onclick) {
                Icon(
                    Icons.Default.Menu,
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        },
        actions = {

            // 1. Add an IconButton to trigger the menu
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            // 2. The DropdownMenu will now open when expanded is set to true
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Sig Out")
                    },
                    onClick = {
                        Toast.makeText(context,"You LogOut The App", Toast.LENGTH_SHORT).show()
                        expanded = false
                    },

                    leadingIcon = {
                        Icon(
                          imageVector =   Icons.AutoMirrored.Filled.Logout,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },

                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black
        )

    )
}