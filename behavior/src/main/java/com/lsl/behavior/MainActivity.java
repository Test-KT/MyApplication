package com.lsl.behavior;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

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
        findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() - v.getWidth() / 2);
                        v.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    execExtractAssetsFile2Sd("test.zip", Environment.getExternalStorageDirectory());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void execExtractAssetsFile2Sd(String zippath, File unzippath) throws IOException {

        InputStream inputStream;
        if (unzippath.isDirectory() && !unzippath.exists()) {
            unzippath.mkdirs();
        }
        inputStream = mContext.getAssets().open(zippath);
        ZipInputStream zipInput = new ZipInputStream(inputStream);
        ZipEntry entry = zipInput.getNextEntry();
        File zipfile;
        byte[] buff = new byte[1024];
        int len;
        while (entry != null) {
            if (entry.isDirectory()) {
                zipfile = new File(unzippath + File.separator + entry.getName());
                if (!zipfile.exists()) {
                    zipfile.mkdirs();
                }
            } else {
                zipfile = new File(unzippath + File.separator + entry.getName());
                if (!zipfile.exists()) {
                    zipfile.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(zipfile);

                while ((len = zipInput.read(buff)) != 0) {
                    fileOutputStream.write(buff, 0, len);
                }
                fileOutputStream.close();
            }
            entry = zipInput.getNextEntry();
        }
        zipInput.close();
        inputStream.close();


    }
}
