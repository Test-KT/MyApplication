package com.lsl.statusdemo

import android.app.Activity
import android.util.Log
import android.widget.Toast

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/1.
 */

fun Activity.setStatusBarDarkMode(darkmode: Boolean) {
    val os = android.os.Build.BRAND
    Log.e("info---->", os)

    Toast.makeText(applicationContext, os, Toast.LENGTH_SHORT).show()

    when (android.os.Build.BRAND) {
        "Xiaomi" -> {
            try {
                var flag = 0
                val clazz = window.javaClass
                val layoutparm = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutparm.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                flag = field.getInt(layoutparm)
                var method = clazz.getMethod("setExtraFlags", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType)
                method.invoke(window, if (darkmode) flag else 0, flag)
            } catch (e: Exception) {
                Log.e("info-->", e.message)
            }

        }
        "Meizu" -> {
//            public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {    boolean result = false;    if (activity != null) {        try {
//                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//                Field darkFlag = WindowManager.LayoutParams.class
//                .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
//                Field meizuFlags = WindowManager.LayoutParams.class
//                .getDeclaredField("meizuFlags");
//                darkFlag.setAccessible(true);
//                meizuFlags.setAccessible(true);            int bit = darkFlag.getInt(null);            int value = meizuFlags.getInt(lp);            if (dark) {
//                    value |= bit;
//                } else {
//                    value &= ~bit;
//                }
//                meizuFlags.setInt(lp, value);
//                activity.getWindow().setAttributes(lp);
//                result = true;
//            } catch (Exception e) {
//            }
//            }    return result;
//            }

        }
    }
}


