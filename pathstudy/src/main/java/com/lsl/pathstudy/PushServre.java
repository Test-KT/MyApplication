package com.lsl.pathstudy;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/6/2.
 */

public class PushServre extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("info--->", "服务创建了");
    }
}
