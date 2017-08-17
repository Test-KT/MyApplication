package com.example.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lsl on 17-8-17.
 */

public class MessenageService extends Service {


    private static final class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.e("info--->", msg.getData().getString("name"));
                    Messenger reply = msg.replyTo;
                    Message replymsg = Message.obtain(null, 2);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", "lsl");
                    replymsg.setData(bundle);
                    try {
                        reply.send(replymsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    private final Messenger messenger = new Messenger(new MessageHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
