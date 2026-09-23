package com.example.jetpackcomposes4

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


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
                           Locale.getDefault()
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


@Composable
fun VideoPlayer() {

    //videoURL
   val videURL = "https://docs.evostream.com/sample_content/assets/bunny.mp4"

    //ExoPlayer needs this context to be created.
    val context = LocalContext.current

    //You create an ExoPlayer instance.
    val player = remember {

    ExoPlayer.Builder(context).build().apply {

        //setMediaItem tells the player what video to load.
        setMediaItem(MediaItem.fromUri(videURL))
    }
}
    //You create a PlayerView, which is the standard ExoPlayer UI that shows the video + controls.
    val playerView = PlayerView(context)

    //rememberSaveable means it survives configuration changes (like screen rotation).
    val playWhenReady by rememberSaveable {

        //This variable controls whether the video should start playing automatically.
        mutableStateOf(true)
    }

    //attach the ExoPlayer to the PlayerView.
    //This is what makes the PlayerView show our video
    //Without this line, the video would not appear.
    playerView.player = player

    LaunchedEffect(player) {
        //Prepares the video (buffers, loads info).
        //Without it, the player won’t start.
        player.prepare()
        //Tells the player whether to start playing immediately or wait.
        player.playWhenReady = playWhenReady
    }
    // Optional: Release player when Composable leaves

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    //lets you place a normal Android View inside Jetpack Compose.

    AndroidView(

        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(16.dp)),

        //Return the PlayerView you created —
        //this is what displays the video on screen.
        factory = {
            playerView

        })
}









