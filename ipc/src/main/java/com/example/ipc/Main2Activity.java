package com.example.ipc;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //top1:use intentService msg exchange
        /*
        Bundle bundle = getIntent().getBundleExtra("mainbun");
        if (bundle != null) {
            String name = bundle.getString("name");
            Log.e("info-->", name);
        } else {
            Log.e("info-->", "NOTICES  data");
        }
    */
        //top2: use file exchage msg
        getWriteUser();
    }


    private void getWriteUser() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/user", "user.txt");
        if (file.exists()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                User user = (User) objectInputStream.readObject();
                Log.e("info--->", user.name + " " + user.id);

                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
