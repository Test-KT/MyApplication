package com.example.zipfile;

import android.content.Context;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .detectAll()
                .penaltyLog()
                //penaltyDeath()
                .build());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    unzip();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void unzip() throws IOException {
        InputStream inputStream = mContext.getAssets().open("test.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        byte[] buff = new byte[1024];
        int len;
        File path = new File(Environment.getExternalStorageDirectory() + File.separator + "ZipFileTest");
        if (!path.exists()) {
            path.mkdirs();
        }
        File temp;
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            if (zipEntry.isDirectory()) {
                temp = new File(path, zipEntry.getName());
                temp.mkdir();
            } else {
                temp = new File(path, zipEntry.getName());
                if (!temp.getParentFile().exists()) {
                    temp.getParentFile().mkdirs();
                }
                FileOutputStream outputStream = new FileOutputStream(temp);
                while ((len = zipInputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                }
                outputStream.flush();
                outputStream.close();
            }
            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.close();
        inputStream.close();
    }
}
