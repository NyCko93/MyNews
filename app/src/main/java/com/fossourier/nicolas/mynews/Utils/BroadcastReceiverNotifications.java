package com.fossourier.nicolas.mynews.Utils;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
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


public class BroadcastReceiverNotifications extends BroadcastReceiver {

    private static SharedPreferences mSharedPreferences;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        mSharedPreferences = SharedPreferences.getInstance(mContext);
        executeRequest();
    }

    public void executeRequest() {
        String search = mSharedPreferences.getQueryTermNotifications();
        List<String> listSections = mSharedPreferences.getSectionOfNotifications();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        Log.i(getClass().getSimpleName(),
                search + mContext.getResources()+ date + mContext.getResources());
        NewYorkTimesStreams.streamArticleSearch(search,listSections,date,date).subscribe(new Observer<SearchArticle>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(SearchArticle searchArticle) {
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
        String keywords = mSharedPreferences.getQueryTermNotifications();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(mContext.getResources()
                .getString(R.string.notificationTitle));
        inboxStyle.addLine(mContext.getResources().getString(R.string.yourResearch)
                + keywords
                + searchArticle.getResponse().getDocs().size() +
                mContext.getResources().getString(R.string.articles));
        String chanelID = mContext.getResources().getString(R.string.chanelID);
        NotificationCompat.Builder notifBuilder = new NotificationCompat
                .Builder(mContext, chanelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mContext.getResources()
                        .getString(R.string.notificationTitle))
                .setContentText(mContext.getResources().getString(R.string.yourResearch)
                        + keywords
                        + searchArticle.getResponse().getDocs().size() +
                        mContext.getResources().getString(R.string.articles))
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setStyle(inboxStyle);
        NotificationManager notifManager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
