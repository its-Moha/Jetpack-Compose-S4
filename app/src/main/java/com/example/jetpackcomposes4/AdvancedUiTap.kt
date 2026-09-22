package com.example.jetpackcomposes4

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


//This function creates a reusable dialog.
//┌─────────────────────────┐
//│                         │
//│      YOUR CONTENT       │
//│                         │
//│ [Dismiss]     [Confirm] │
//└─────────────────────────┘
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

        //This function creates a reusable dialog.
        //This means:
        //"Give me any Compose UI you want, and I will put it inside the dialog."
        //That's why you can put a TimePicker in one place and a DatePicker in another.
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

    //This gets the current date and time from the device.
    val currentTime = Calendar.getInstance()

    //This creates the state that controls the TimePicker.
    //Hour   = 16
    //Minute = 25
    //It keeps track of things like:
    // the selected hour the selected minute and whether the picker uses 24-hour format
    val timePickerState = rememberTimePickerState(
        //This gets the current hour from currentTime.
        initialHour = currentTime.get(Calendar.HOUR),

        //This gets the current minute.
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    Column{

        Button(
            onClick = {
                showDialog = true
            },

        ) {
            Text("Display Time")
        }

        //Only show the dialog when showDialog is true.
        if (showDialog) {

            DateAndTimeDialog(
                onDismiss = {
                    showDialog = false
                },

                onConfirm = {
                    // formats the time
                    //hour = 9
                    //minute = 5
                    //becomes: 09:05 instead of  9:5

                    showResult = String.format(
                        "%02d:%02d",
                        timePickerState.hour,
                        timePickerState.minute
                    )

                    if (showResult.isNotEmpty()){
                        Toast.makeText(context,"Time Is Added", Toast.LENGTH_SHORT).show()
                    }

                    // after pressing confirm close the dialog
                    showDialog = false
                }
            ) {


                //Displays the TimePicker UI and connects it to that state.
                TimePicker(state = timePickerState)
            }
        }

        if (showResult.isNotEmpty()){
            Text("time is: $showResult", fontSize = 20.sp)
        }
    }
}


@Composable
fun DatePickerExample() {

    var showDialog by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf("") }
    val context = LocalContext.current
    val currentDate = Calendar.getInstance()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = currentDate.timeInMillis
    )

    Column{
        Button(
            onClick = {
                showDialog = true
            }
        ) {
            Text("Display Date")
        }

        if (showDialog){

           DateAndTimeDialog(
               onDismiss = {
                   showDialog = false
               },
               onConfirm =  {

                   // get selected date in milliseconds
                   val selectedDateInMillis = datePickerState.selectedDateMillis

                   // convert milliseconds to readable date
                   selectedDateInMillis?.let {
                       val formatter = SimpleDateFormat(
                           "dd/MM/yyyy",
                           java.util.Locale.getDefault()
                       )

                       //Date(it) creates a Date object from the milliseconds value.
                       //Converts milliseconds into a Date object
                       //
                       //formatter.format(Date(it)) converts that Date object into readable text.
                       val readableStringDate = formatter.format(Date(it))

                       showResult = readableStringDate

                       Toast.makeText(context, "Your Date Is Added", Toast.LENGTH_SHORT).show()

                       showDialog = false
                   }
               }

           ) {
               DatePicker(state = datePickerState)
           }

        }

        if (showResult.isNotEmpty()){
            Text("Date is: $showResult", fontSize = 20.sp)
        }
    }

}















