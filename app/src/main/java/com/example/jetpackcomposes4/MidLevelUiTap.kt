package com.example.jetpackcomposes4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size


@Composable
fun LoadImage() {

    val imageUrl = "https://mrwallpaper.com/images/high/1080p-hd-cottage-by-the-lake-ozfn18uzcfe0h7oq.webp"

    Column(
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(10.dp)
            .fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround)

    {

        Text(
            text = "Load Image From Internet",
            fontSize = 15.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textAlign = TextAlign.Center
        )


        val model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .size { Size.ORIGINAL }
            .build()

        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)),
            model = model,
            contentDescription = "image",

            )

    }


    }























//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.ImageNotSupported
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import coil3.compose.AsyncImage
//import coil3.compose.AsyncImagePainter
//import coil3.compose.rememberAsyncImagePainter
//import coil3.request.ImageRequest
//import coil3.size.Size
//
//@Composable
//fun LoadImage() {
//
//    val imageUrl = "https://mrwallpaper.com/images/high/1080p-hd-cottage-by-the-lake-ozfn18uzcfe0h7oq.webp"
//
//    val model = ImageRequest
//        .Builder(LocalContext.current)
//        .data(imageUrl)
//        .size(Size.ORIGINAL)
//        .build()
//
//    AsyncImage(
//        model = model ,
//        contentDescription = "Image",
//        modifier = Modifier
//            .padding(15.dp)
//            .clip(RoundedCornerShape(10.dp)),
//    )
//
//    //if it loads or not the state will tell us
//    val imageState = rememberAsyncImagePainter(model = model).state
//
//    if (imageState is AsyncImagePainter.State.Success){
//        Image(
//            painter = imageState.painter,
//            contentDescription = "image",
//            modifier = Modifier
//                .clip(RoundedCornerShape(10.dp)),
//
//        )
//    }
//
//    if (imageState is AsyncImagePainter.State.Loading){
//        CircularProgressIndicator(
//            modifier = Modifier
//                .size(100.dp),
//            color = MaterialTheme.colorScheme.primary
//        )
//
//    }
//    if (imageState is AsyncImagePainter.State.Error){
//
//        Icon(
//            modifier = Modifier
//                .size(100.dp),
//
//            imageVector = Icons.Rounded.ImageNotSupported,
//            contentDescription = "image not supported",
//            tint = MaterialTheme.colorScheme.error)
//
//
//
//    }
//}
