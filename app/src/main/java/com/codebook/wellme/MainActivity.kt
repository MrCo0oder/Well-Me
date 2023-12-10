package com.codebook.wellme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codebook.wellme.navigation.AppNavigationGraph
import com.codebook.wellme.ui.theme.WellMeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        actionBar?.hide()
        installSplashScreen()
        Thread.sleep(1500)
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        setContent {
            WellMeTheme {
                AppNavigationGraph()
            }
        }
        parseIntent(intent)
    }

    private fun parseIntent(intent: Intent?) {
        val isMedicationNotification =
            intent?.getBooleanExtra(MEDICATION_NOTIFICATION, false) ?: false
        Log.i( "isMedicationNotification: ",isMedicationNotification.toString())
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationService.MEDICATION_CHANNEL_ID,
               "Medication Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Notifications for medication reminder"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}