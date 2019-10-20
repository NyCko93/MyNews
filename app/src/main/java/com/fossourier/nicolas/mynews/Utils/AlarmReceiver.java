package com.fossourier.nicolas.mynews.Utils;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.fossourier.nicolas.mynews.Models.SearchArticle;
import com.fossourier.nicolas.mynews.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class AlarmReceiver extends BroadcastReceiver {

    private static SharedPreferences mSharedPreferences;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mSharedPreferences = SharedPreferences.getInstance(mContext);
        executeRequest();
    }


    public void executeRequest() {
           //------------------------------------//
          // Execute request with data received //
         //------------------------------------//
        // Get the query term used
        String search = mSharedPreferences.getQueryTermNotifications();
        // Get the list of section(s) checked
        List<String> listSections = mSharedPreferences.getSectionOfNotifications();
        // Get the date
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        Log.i(getClass().getSimpleName(),
                search + mContext.getResources() + date + mContext.getResources());
        NewYorkTimesStreams.streamArticleSearch(search, listSections, date, date).subscribe(new Observer<SearchArticle>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(SearchArticle searchArticle) {
                showNotification(searchArticle);
            }
            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }
            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

    @SuppressLint("ObsoleteSdkInt")
    private void showNotification(SearchArticle searchArticle) {
           //--------------------------//
          // Show notification system //
         //--------------------------//
        // Get the query term used
        String keywords = mSharedPreferences.getQueryTermNotifications();
        // Inbox for notifBuilder
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(mContext.getResources()
                .getString(R.string.notificationTitle));
        inboxStyle.addLine(mContext.getResources().getString(R.string.yourResearch) + keywords + mContext.getResources().getString(R.string.found) + searchArticle.getResponse().getDocs().size() + mContext.getResources().getString(R.string.articles));
        // We create a channel for notifications
        String chanelID = mContext.getResources().getString(R.string.chanelID);
        // The notifBuilder for display a notification system
        NotificationCompat.Builder notifBuilder = new NotificationCompat
                .Builder(mContext, chanelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mContext.getResources()
                        .getString(R.string.notificationTitle))
                .setContentText(mContext.getResources().getString(R.string.yourResearch) + keywords + mContext.getResources().getString(R.string.found) + searchArticle.getResponse().getDocs().size() + mContext.getResources().getString(R.string.articles))
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setStyle(inboxStyle);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notifManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        // We should guard this code with a condition on the SDK_INT version to run only on Android 8.0 (API level 26) and higher,
        // because the notification channels APIs are not available in the support library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence chanelName = mContext.getResources().getString(R.string.notif_other_sdk);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notifChanel = new NotificationChannel(chanelID, chanelName, importance);
            assert notifManager != null;
            notifManager.createNotificationChannel(notifChanel);
            notifManager.notify(0, notifBuilder.build());
        }
    }
}
