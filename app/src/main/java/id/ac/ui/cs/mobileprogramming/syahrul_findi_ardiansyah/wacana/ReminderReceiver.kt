package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {

        val intent = Intent(context, ReminderService::class.java)

        context?.startService(intent)
    }
}