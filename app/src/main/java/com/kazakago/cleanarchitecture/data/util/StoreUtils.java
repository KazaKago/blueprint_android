package com.kazakago.cleanarchitecture.data.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * PlayStore Related Utilities
 *
 * @author PTAMURA
 */
public class StoreUtils {

    public static String getStoreAppLink(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "market://details?id=" + packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getBrowserAppLink(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "http://play.google.com/store/apps/details?id=" + packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
