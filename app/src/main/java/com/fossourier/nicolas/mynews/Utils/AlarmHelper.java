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

        AlarmManager mAlarmManager;
        PendingIntent mPendingIntent;

        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (switchNotif) {
            Intent intent = new Intent(context, BroadcastReceiverNotifications.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            if (mAlarmManager != null) {
                mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifTime.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, mPendingIntent);
                AlarmToastHelper.alarmToast(true, context);
            }
        }

        if (mAlarmManager != null && switchNotif) {
            Intent intent = new Intent(context, BroadcastReceiverNotifications.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notifTime.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, mPendingIntent);
            AlarmToastHelper.alarmToast(true, context);
        }
        if (!switchNotif && mAlarmManager != null) {
            Intent intent = new Intent(context, BroadcastReceiverNotifications.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            mAlarmManager.cancel(mPendingIntent);
            AlarmToastHelper.alarmToast(false, context);
        }
    }

     static class AlarmToastHelper {

        /**
         * send a toast when alarm is set or unset
         *
         * @param state   : (bool) true is enable, false is disable
         * @param context : (Context) activity context
         */

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
