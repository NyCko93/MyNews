package com.fossourier.nicolas.mynews.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Objects;

// Test of connectivity at network
public class Connectivity {

    public static boolean networkInfo (Context context){
        // Get the network info
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(context).getSystemService(Context.CONNECTIVITY_SERVICE);
        // require NonNull
        assert connectivityManager != null;
        // Check if there is any connectivity
        NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return mNetworkInfo != null && mNetworkInfo.isConnected();
    }
}
