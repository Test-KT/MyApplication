package com.lsl.pathstudy;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/2.
 */

@SuppressLint("OverrideAbstract")
public class TestService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {   //通知来了回调
        super.onNotificationPosted(sbn);

        Log.e("infoo--->", "通知来了");
        Intent intent = new Intent(this, PushServre.class);  //做具体业务
        startService(intent);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {  //通知移除的的时候回调
        super.onNotificationRemoved(sbn);
        Log.e("infoo--->", "移除了来了");  //做具体业务

    }

    @Override
    public void onListenerConnected() {   //通知管理器的连接时候回调
        super.onListenerConnected();
    }


    private void toggleNotificationListenerService() {
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName(this, TestService.class),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(new ComponentName(this, TestService.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

}
