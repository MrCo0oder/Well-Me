package com.codebook.wellme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codebook.wellme.navigation.AppNavigationGraph
import com.codebook.wellme.service.StopwatchService
import com.codebook.wellme.ui.theme.WellMeTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isBound by mutableStateOf(false)

    @OptIn(ExperimentalAnimationApi::class)
    private lateinit var stopwatchService: StopwatchService

    @OptIn(ExperimentalAnimationApi::class)
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as StopwatchService.StopwatchBinder
            stopwatchService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        actionBar?.hide()
        installSplashScreen()
        Thread.sleep(1500)
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        Log.d("uuid", UUID.randomUUID().toString())
//        val fb=FirebaseMessaging.getInstance()
//        fb.subscribeToTopic("new_user_forums")
        setContent {
            WellMeTheme {
                if (isBound)
                    AppNavigationGraph(stopwatchService)
            }
        }
        parseIntent(intent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onStart() {
        super.onStart()
        Intent(this, StopwatchService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun parseIntent(intent: Intent?) {
        val isMedicationNotification =
            intent?.getBooleanExtra(MEDICATION_NOTIFICATION, false) ?: false
        Log.i("isMedicationNotification: ", isMedicationNotification.toString())
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            NotificationService.MEDICATION_CHANNEL_ID,
            "Medication Reminder",
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = "Notifications for medication reminder"

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }
}