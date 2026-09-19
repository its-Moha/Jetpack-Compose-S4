package com.example.jetpackcomposes4

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import java.util.Calendar


@Composable
fun DateAndTimeDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,

    //By adding @Composable to the function type,
    // you allow the caller to pass in other UI elements
    // (like a TimePicker) to be displayed inside the dialog.
    content: @Composable () -> Unit,
    ) {


    //creates the pop-up window.
    AlertDialog(

        //This handles "system" dismiss events, such as
        // when a user taps outside the dialog or presses the Back button.
        onDismissRequest = onDismiss,

        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Dismiss")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Confirm")
            }
        },

        text = {
            content()
        },

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun TimePickerExample() {
    var showDialog by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf("") }
    val context = LocalContext.current
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally  // Center all children
    ) {

        Button(
            onClick = {
                showDialog = true
            },

        ) {
            Text("Show Time")
        }


        if (showDialog) {
            DateAndTimeDialog(
                onDismiss = {
                    showDialog = false
                },

                onConfirm = {
                    showResult = String.format(
                        "%02d:%02d",
                        timePickerState.hour,
                        timePickerState.minute
                    )

                    if (showResult.isNotEmpty()){
                        Toast.makeText(context,"Time Is Added", Toast.LENGTH_SHORT).show()
                    }

                    showDialog = false
                }
            ) {
                TimePicker(state = timePickerState)
            }
        }

        if (showResult.isNotEmpty()){
            Text("time is: $showResult", fontSize = 20.sp)
        }
    }
}


















