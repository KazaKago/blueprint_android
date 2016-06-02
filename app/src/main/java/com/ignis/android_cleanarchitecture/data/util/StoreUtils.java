package com.ignis.android_cleanarchitecture.data.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * ストア関連のUtilクラス
 *
 * @author PTAMURA
 */
public class StoreUtils {

    /**
     * 自身のPlayストアリンクを取得する(Playストアアプリへの直接遷移)
     *
     * @param context
     * @return
     */
    public static String getStoreDirectLink(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "market://details?id=" + packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 自身のPlayストアリンクを取得する(ブラウザでも開けるバージョン)
     *
     * @param context
     * @return
     */
    public static String getStoreGeneralLink(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return "http://play.google.com/store/apps/details?id=" + packageInfo.packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
