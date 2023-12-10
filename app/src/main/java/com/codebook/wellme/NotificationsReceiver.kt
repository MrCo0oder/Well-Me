package com.codebook.wellme

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import com.codebook.wellme.ui.theme.PeachLight


const val MEDICATION_INTENT = "medication_intent"
const val MEDICATION_NAME = "MEDICATION_NAME"
const val MEDICATION_NOTIFICATION = "medication_notification"

class NotificationsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { context ->
            intent?.let {
                showNotification(context, it.getLongExtra(MEDICATION_INTENT, 0),it.getStringExtra(MEDICATION_NAME)!!)
            }

        }
    }

    private fun showNotification(context: Context, medicationTime: Long,medicationName: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.putExtra(MEDICATION_NOTIFICATION, true)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
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
            context,
            NotificationService.MEDICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.logo_small)
            .setContentTitle("Medication Reminder")
            .setContentText(context.getString(R.string.medication_reminder_time, medicationName))
            .setContentIntent(activityPendingIntent)
            .build()

        // TODO: Use medication id as notification id.
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(medicationTime.toInt(), notification)
    }
}