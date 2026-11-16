package com.example.jetpackcomposes4

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.jvm.java


@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
fun showNotification(context: Context) {

    val channelId = "myChannel" //Unique ID for the channel.
    val notificationChannel = "My Notifications"  // Name displayed in Android settings.
    val importance = NotificationManager.IMPORTANCE_DEFAULT

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            notificationChannel,
            importance
        ).apply {
            description = "This Is Notification Channel"
        }

        val manager = context.getSystemService(
            NotificationManager::class.java
        )

        manager.createNotificationChannel(channel)
    }


    // Create an explicit intent for an Activity in your app
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)


    val notification = NotificationCompat.Builder(
        context, channelId
    )
        .setSmallIcon(R.drawable.notification)
        .setContentTitle("Hello!")
        .setContentText("Welcome to my application.")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    NotificationManagerCompat.from(context)
        .notify(1, notification)
}