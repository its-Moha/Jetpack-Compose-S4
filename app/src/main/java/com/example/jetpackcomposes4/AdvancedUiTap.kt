package com.example.jetpackcomposes4

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateAndTimePickerDialog(

    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content:@Composable () -> Unit
) {

    androidx.compose.material3.AlertDialog(
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
        }
    )
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerExample() {

   var showDialog by remember { mutableStateOf(false) }

   val currentTime = Calendar.getInstance()

   var showResult by remember { mutableStateOf("") }


    val context = LocalContext.current

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true

    )


    Column {
        Button(onClick = {
            showDialog = true
        }) {
            Text("Show Time Dialog", textAlign = TextAlign.Center)
        }

        if (showDialog){
            DateAndTimePickerDialog(
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
                        Toast.makeText(context,"your time is added", Toast.LENGTH_SHORT).show()
                    }
                    showDialog = false
                }
            ) {
                TimePicker(state = timePickerState)
            }
        }

        if (showResult.isNotEmpty()){
            Text("Time is: $showResult", textAlign = TextAlign.Center)
        }
    }
}