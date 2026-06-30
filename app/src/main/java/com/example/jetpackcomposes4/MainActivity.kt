package com.example.jetpackcomposes4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposes4.ui.theme.JetpackComposeS4Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeS4Theme {

                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Text()
                    MyTabs()

                }
            }
        }
    }
}


@Composable
fun MyTabs() {

    val tablist = listOf("Basic Ui", "Mid-Level UI", "Advanced Ui")

    //keeps track of which page (tab) is currently visible.
    val pagerState = rememberPagerState(

        initialPage = 1, //start on the first tab
        pageCount = {
            tablist.size //tells the pager how many pages there are.
        }
    )

    val scope = rememberCoroutineScope()

    Column() {

        //Creates the row of tab buttons at the top.
        PrimaryTabRow(
            selectedTabIndex = pagerState.currentPage,

            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primaryContainer,


        ) {

            //This goes through each tab in the list:  //index → number (0, 1, 2)  //title → text ("Home", "Profile", "Settings")
            tablist.forEachIndexed { index, title ->

                Tab(

                    //highlights the tab if it matches the current page.
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            //Clicking a tab calls scrollToPage(index), which scrolls the pager to the right page.

                            pagerState.scrollToPage(index)
                        }
                    },
                  text = {
                      //shows the tab’s text.
                      Text(title)
                  }
                )
            }
        }

        //The swipeable pager that holds all tab screens.
        //shows one screen at a time and lets you swipe left/right.

        HorizontalPager(
            //connects it to the tab buttons.
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page -> //is the index of the current page being shown.
            when(page){
              0 -> Tap1 ()
              1 -> Tap2 ()
              2 -> Tap3 ()
            }
        }
    }
}


@Composable
fun Tap1(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        LearnColumn()
        LearnRow()
        LearnBox()
        Counter()
        TextState()
        TextVisibilityState()
        TextFieldExample()
        ImageExample()

    }
}


@Composable
fun Tap2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoadImage()
        Dialog()
        TodoListCheckbox()

    }
}
@Composable
fun Tap3() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {


    }
}





@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackComposeS4Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text()
            MyTabs()
        }
    }
}