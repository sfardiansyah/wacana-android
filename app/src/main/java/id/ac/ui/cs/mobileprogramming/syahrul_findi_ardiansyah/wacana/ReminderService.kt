package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

private const val CHANNEL_NAME = "REMINDER_CHANNEL_WACANA"
private const val CHANNEL_ID = "REMINDER_CHANNEL_ID"
private const val CHANNEL_DESC = "REMINDER_CHANNEL_DESC"

class ReminderService : IntentService("ReminderService") {

    private lateinit var reminderNotificationManager: NotificationManager

    override fun onHandleIntent(intent: Intent?) {
        sendNotification("You have items in your shopping cart to checkout!")
    }

    private fun sendNotification(msg: String) {
        createNotificationChannel()

        reminderNotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val contentIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            0
        )

        val reminderNotificationBuilder =
            NotificationCompat.Builder(this).setContentTitle("Reminder!")
                .setSmallIcon(R.drawable.ic_logo_wacana)
                .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setChannelId(CHANNEL_ID)

        reminderNotificationBuilder.setContentIntent(contentIntent)
        reminderNotificationManager.notify(1, reminderNotificationBuilder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = CHANNEL_NAME
            val desc: String = CHANNEL_DESC
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = desc

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
