package com.example.jetpackcomposes4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AndroidScreenTaps(){


    val myTablist = listOf("Topic 1","Topic 2")

    //keeps track of which page (tab) is currently visible.
    val pageState = rememberPagerState(
        initialPage = 0, //start on the first tab
        pageCount = { myTablist.size } //tells the pager how many pages there are.
    )

    val scope = rememberCoroutineScope()

    Column{

        //Creates the row of tab buttons at the top.
        PrimaryTabRow(
            selectedTabIndex = pageState.currentPage,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primaryContainer
        ) {

            //This goes through each tab in the list:  //index → number (0, 1, 2)  //title → text ("Home", "Profile", "Settings")
            myTablist.forEachIndexed { index, title ->
                Tab(

                    //highlights the tab if it matches the current page.
                    selected = pageState.currentPage == index,
                    onClick = {
                        scope.launch {
                            //Clicking a tab calls scrollToPage(index), which scrolls the pager to the right page.
                            pageState.scrollToPage(index)
                        }
                    },
                    //shows the tab’s text.
                    text = {
                        Text(title)
                    }

                )
            }
        }


        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize()
        ) {page -> //is the index of the current page being shown.
            when (page){
                0 -> Tap4()
                1 -> Tap5()
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Tap4() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        val context = LocalContext.current



       val permissionLauncher = rememberLauncherForActivityResult(
           contract = ActivityResultContracts.RequestPermission()
       ) { isGranted ->

           if (isGranted){
               createNotificationChannel(context)
           }
       }

        Button(
            onClick = @androidx.annotation.RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    permissionLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                } else {
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.POST_NOTIFICATIONS
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                    }
                    createNotificationChannel(context)
                }
            }
        ) {
            Text(
                "Show Notification",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}
@Composable
fun Tap5() {
    TODO("Not yet implemented")
}


