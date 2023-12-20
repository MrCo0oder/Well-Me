package com.codebook.wellme.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.codebook.wellme.MEDICATION_NOTIFICATION
import com.codebook.wellme.MainActivity
import com.codebook.wellme.NotificationService
import com.codebook.wellme.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService() : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        val notificationChannel = NotificationChannel(
//            message.messageId.toString(),
//            message.notification?.title, NotificationManager.IMPORTANCE_HIGH
//        )
//        getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
//        val notification=Notification.Builder(this,  message.messageId.toString())
//            .setContentTitle(message.notification?.title)
//            .setContentText(message.notification?.body)
//            .setSmallIcon(R.drawable.logo_small)
//            .setAutoCancel(true)
//            .build()
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        NotificationManagerCompat.from(this).notify(message.messageId.hashCode(),notification)
        val activityIntent = Intent(this, MainActivity::class.java)
        activityIntent.putExtra(MEDICATION_NOTIFICATION, true)
        val activityPendingIntent = PendingIntent.getActivity(
            this,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        /*val takenPendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            receiverIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )*/

        // TODO: Add action.
        val notification = NotificationCompat.Builder(
            this,
            NotificationService.MEDICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.logo_small)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)
            .build()

        // TODO: Use medication id as notification id.
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(message.messageId.hashCode(), notification)
    }
    }
