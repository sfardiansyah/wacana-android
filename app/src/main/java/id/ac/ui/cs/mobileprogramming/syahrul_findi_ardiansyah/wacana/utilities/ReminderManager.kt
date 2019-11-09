package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ReminderReceiver

class ReminderManager {

    companion object {

        fun cancelReminder(context: Context) {
            val receiver = Intent(context, ReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 1, receiver, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.cancel(pendingIntent)
        }

        fun sendReminderWithAlarm(context: Context) {
            val receiver = Intent(context, ReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 1, receiver, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setExact(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 1 * 60000,
                pendingIntent
            )
        }
    }
}