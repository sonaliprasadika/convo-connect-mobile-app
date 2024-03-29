package com.plcoding.notificationpermissions

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                 CounterNotificationService.COUNTER_CHANNEL_ID,
                "Counter",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Used for the increment counter notifications"
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}