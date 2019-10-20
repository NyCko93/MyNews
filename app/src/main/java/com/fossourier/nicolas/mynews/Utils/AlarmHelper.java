package com.fossourier.nicolas.mynews.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fossourier.nicolas.mynews.R;

import java.util.Calendar;

public class AlarmHelper {

    public void configureAlarmNotif(Context context, boolean switchNotif, Calendar notifTime) {
           //---------------------------------------------//
          //  DISPLAY A TOAST WITH THE STATUT OF ALARM   //
         //  Notifications Enable if switchNotif = true //
        //---------------------------------------------//
        AlarmManager mAlarmManager;
        PendingIntent mPendingIntent;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // AlarmManager activate, update data
        if (switchNotif) {
            Intent intent = new Intent(context, AlarmReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            if (mAlarmManager != null) {
                mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifTime.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, mPendingIntent);
                AlarmToastHelper.alarmToast(true, context);
            }
        }
        // AlarmManager activate, hour per default
        if (mAlarmManager != null && switchNotif) {
            Intent intent = new Intent(context, AlarmReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifTime.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, mPendingIntent);
            AlarmToastHelper.alarmToast(true, context);
        }
        // AlarmManager desactivate if no NotifSwitch
        if (!switchNotif && mAlarmManager != null) {
            Intent intent = new Intent(context, AlarmReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            mAlarmManager.cancel(mPendingIntent);
            AlarmToastHelper.alarmToast(false, context);
        }
    }
     static class AlarmToastHelper {
           //-------------------------------------------//
          // DISPLAY A TOAST WITH THE STATUT OF ALARM  //
         //-------------------------------------------//
        static void alarmToast(boolean state, Context context) {
            if (state) {
                Toast.makeText(context,
                        context.getResources().getString(R.string.ALARM_ACTIVATE),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,
                        context.getResources().getString(R.string.ALARM_DESACTIVATE),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
