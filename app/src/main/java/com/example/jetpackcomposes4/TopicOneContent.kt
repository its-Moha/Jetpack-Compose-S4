package com.example.jetpackcomposes4

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


@SuppressLint("SuspiciousIndentation")
@RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
fun createNotificationChannel(context: Context){

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

        val channel = NotificationChannel(
            "myChannel",
            "my_Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "This is my notification Channel"
        }

        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }




    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent  = PendingIntent.getActivity(context,0,intent, PendingIntent.FLAG_IMMUTABLE)

    val notification = NotificationCompat.Builder(
            context, "myChannel"
        )

            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Hello")
            .setContentText("This is my Notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()


        NotificationManagerCompat.from(context)
            .notify(1,notification)
    }

