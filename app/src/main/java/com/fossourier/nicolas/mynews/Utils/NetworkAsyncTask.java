//package com.fossourier.nicolas.mynews.Utils;
//
//import android.util.Log;
//
//import java.lang.ref.WeakReference;
//
//public class NetworkAsyncTask extends android.os.AsyncTask<String, Void, String> {
//
//    public interface Listeners {
//        void onPreExecute();
//        void doInBackground();
//        void onPostExecute(String success);
//    }
//
//    private final WeakReference<Listeners> callback;
//
//    public NetworkAsyncTask(Listeners callback){
//        this.callback = new WeakReference<>(callback);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        this.callback.get().onPreExecute();
//        Log.e("TAG", "AsyncTask est démarré.");
//    }
//
//    @Override
//    protected void onPostExecute(String success) {
//        super.onPostExecute(success);
//        this.callback.get().onPostExecute(success);
//        Log.e("TAG", "AsyncTask est terminé.");
//    }
//
//    @Override
//    protected String doInBackground(String... url) {
//        this.callback.get().doInBackground();
//        Log.e("TAG", "AsyncTask fait un gros travail...");
//        return MyHttpURLConnection.startHttpRequest(url[0]);
//    }
//}