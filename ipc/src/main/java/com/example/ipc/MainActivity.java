package com.example.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    //top3
    private Messenger messenger;

    private static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Log.e("info--->", msg.getData().getString("name"));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }

    private Messenger mes = new Messenger(new MessageHandler());

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message message = Message.obtain(null, 1);
            Bundle bundle = new Bundle();
            bundle.putString("name", "nlt");
            message.setData(bundle);
            message.replyTo = messenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //top1:use intentService msg exchange
                /*
                Intent intent = new Intent(MainActivity.this, IPCIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "lsl");
                intent.putExtra("mainbun", bundle);
                startService(intent);
                */
                //top2: use file exchange msg
               /*
                User user = new User();
                user.name = "lsl";
                user.id = 1;
                writeUser(user);
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                */

                //top3: use Messenager exchange msg
                Intent intent = new Intent(MainActivity.this, MessenageService.class);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

            }
        });
    }


    private void writeUser(User user) {
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/user");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/user", "user.txt");

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(user);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                try {
                    if (objectOutputStream != null)
                        objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
