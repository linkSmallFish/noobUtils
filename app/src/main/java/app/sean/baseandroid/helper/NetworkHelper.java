package app.sean.baseandroid.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sean on 16/12/10.
 */

public class NetworkHelper {
    /**
     * 检查WIFI是否连接
     */
    public static boolean isWifiConnected( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE );
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo( ConnectivityManager.TYPE_WIFI );
        return wifiInfo != null;
    }

    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    public static boolean isMobileNetworkConnected( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE );
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE );
        return mobileNetworkInfo != null;
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected( Context context ) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE );
        return connectivityManager.getActiveNetworkInfo( ) != null;
    }
}
