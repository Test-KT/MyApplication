package com.example.zipfile;

import android.content.Context;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
//                    unzip();
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "images");
                    execExtractAssetsFile2Sd("test.zip", file);
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


    /**
     * copy asset中的模板缩略图并解压到sd卡中
     *
     * @param zippath   压缩包路径名称
     * @param unzippath 解压的路径
     */
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
                zipfile.mkdir();
                Log.e("info--->","目录："+unzippath + File.separator + entry.getName());
            } else {
                zipfile = new File(unzippath, entry.getName());
                if (!zipfile.getParentFile().exists()) {
                    zipfile.getParentFile().mkdirs();
                }
                Log.e("info--->","文件："+unzippath + File.separator + entry.getName());

                FileOutputStream fileOutputStream = new FileOutputStream(zipfile);

                while ((len = zipInput.read(buff)) != -1) {
                    fileOutputStream.write(buff, 0, len);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            entry = zipInput.getNextEntry();
        }
        zipInput.close();
        inputStream.close();
    }
}
