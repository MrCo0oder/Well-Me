package com.codebook.wellme

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class NotificationService(
    private val context: Context
) {

    fun scheduleNotification(time: Long, name: String) {
        val intent = Intent(context, NotificationsReceiver::class.java)
        intent.putExtra(MEDICATION_INTENT, time)
        intent.putExtra(MEDICATION_NAME, name)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            time.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val alarmService = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        try {
            alarmService.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        } catch (exception: SecurityException) {
            exception.printStackTrace()
        }
    }

    companion object {
        const val MEDICATION_CHANNEL_ID = "medication_channel"
    }
}