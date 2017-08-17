package com.example.ipc;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by lsl on 17-8-17.
 */

public class IPCIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IPCIntentService() {
        super("lsl");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getBundleExtra("mainbun");
        Intent intent1 = new Intent(this, Main2Activity.class);
        intent1.putExtra("mainbun", bundle);
        startActivity(intent1);
    }
}
