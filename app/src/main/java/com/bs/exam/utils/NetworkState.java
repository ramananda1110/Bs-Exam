package com.bs.exam.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by linux64 on 9/26/16.
 */

public class NetworkState {

    public static boolean isNetworkAvailable(Context mContext) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert cm != null;
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                // connected to the internet
                switch (activeNetwork.getType()) {
                    case ConnectivityManager.TYPE_WIFI:
                        // connected to wifi
                        status = true;
                        break;
                    case ConnectivityManager.TYPE_MOBILE:
                        // connected to mobile data

                        status = true;
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return status;
    }
}
