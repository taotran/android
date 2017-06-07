package vn.com.tvtran.myfootball.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tvtran on 2/21/2017.
 */

public class MFUtils {


    // systemService:
    public static boolean isConnectionAvailable(final Object systemService) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) systemService;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
