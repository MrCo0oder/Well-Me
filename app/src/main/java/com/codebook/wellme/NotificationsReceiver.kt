package com.codebook.wellme

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationsReceiver : BroadcastReceiver() {companion object {

    // The id of the channel.
    private val channelId = "my_channel_01"
    private val notifyID: Int = 1
    private val importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
}

    /*   override fun onReceive(context: Context?, intent: Intent?) {
        val mChannel = NotificationChannelCompat.Builder(channelId, importance).apply {
            setName("channel name") // Must set! Don't remove
            setDescription("channel description")
            setLightsEnabled(true)
            setLightColor(Color.CYAN)
            setVibrationEnabled(true)
            setVibrationPattern(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
        }.build()
        val notificationManager: NotificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val repeatingIntent = Intent(context, MainActivity::class.java)
        repeatingIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent =
            if (
                android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S
            ) PendingIntent.getActivity(
                context,
                100,
                repeatingIntent,
                PendingIntent.FLAG_MUTABLE
            ) else
                PendingIntent.getActivity(
                    context,
                    100,
                    repeatingIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
        val notificationBuilder =
            NotificationCompat.Builder(context, channelId).setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.logo_small)
                .setContentTitle("Title")
                .setContentText("Body")
                .setAutoCancel(true)
        notificationManager.notify(100, notificationBuilder.build())
        val notification: Notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(sender)
            .setContentText(body)
            .build()
        NotificationManagerCompat.from(context).notify(notifyID, notification)
        NotificationManagerCompat.from(context).createNotificationChannel(mChannel)

    }*/
    override fun onReceive(context: Context?, intent: Intent?) {
//        context?.let {
//            intent?.getParcelableExtra<Medication>(MEDICATION_INTENT)?.let { medication ->
//                showNotification(it, medication)
//            }
//        }
        if (context != null) {
            showNotification(context)
        }
    }
    private fun showNotification(context: Context) {
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.putExtra("MEDICATION_NOTIFICATION", true)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val receiverIntent = Intent(context, MainActivity::class.java)
        /*val takenPendingIntent = PendingIntent.getBroadcast(
            context,
            2,
            receiverIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )*/

        // TODO: Add action.
        val notification = NotificationCompat.Builder(
            context,
            "MEDICATION_NOTIFICATION"
        )
            .setSmallIcon(R.drawable.logo_small)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText("medication_reminder_time")
            .setContentIntent(activityPendingIntent)
            /*.addAction(
                R.drawable.doctor,
                "Take now",
                takenPendingIntent)*/
            .build()

        // TODO: Use medication id as notification id.
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(100, notification)

    }
}