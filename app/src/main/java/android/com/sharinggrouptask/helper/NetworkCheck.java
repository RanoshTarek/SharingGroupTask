package android.com.sharinggrouptask.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/******************************************************************************
 * Module: NetworkCheck
 *
 * File Name: NetworkCheck.java
 *
 * Description: Source file for Network to check connected or not
 *
 * Author: Rana Tarek
 ******************************************************************************/
public class NetworkCheck {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
