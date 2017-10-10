package com.example.cammer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final int GALLERY_RES = 1001;
    private static final int GALLERY_RES_KIT = 1002;
    private static final int CROP_RES = 1003;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
    }

    /**
     * 使用隐式启动选择图片
     *
     * @param v
     */
    public void doCammer(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        //dui butong sdk banben jin xing qu fen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            startActivityForResult(intent, GALLERY_RES_KIT);
        } else { //< sdk4.4
            startActivityForResult(intent, GALLERY_RES);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GALLERY_RES:
                handleGalleryResult(resultCode, data);
                break;
            case GALLERY_RES_KIT:
                handleGalleryKitResult(resultCode, data);
                break;
            case CROP_RES:
                //huo qu suo lue tu
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                break;
        }
    }

    /**
     * <=4.4
     *
     * @param resultCode
     * @param data
     */
    private void handleGalleryResult(int resultCode, Intent data) {
        String path = data.getData().getPath();
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        File faceFile = null;
        try {
            faceFile = saveBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(faceFile);
        routeToCrop(uri);
    }

    /**
     * >4.4
     *
     * @param resultCode
     * @param data
     */
    private void handleGalleryKitResult(int resultCode, Intent data) {
        File facefile = null;
        try {
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(data.getData(), "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            facefile = saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, "com.example.cammer", facefile);
        } else {
            uri = Uri.fromFile(facefile);
        }

        routeToCrop(uri);

    }

    public File saveBitmap(Bitmap bitmap) throws IOException {

        File file = new File(getExternalCacheDir(), "face-cache");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        //tong zhi geng xin xitong xiangce
//        Uri uri = Uri.fromFile(file);
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE));
        return file;
    }

    /**
     * tupian cai jian ya
     *
     * @param file
     */
    public void routeToCrop(Uri file) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(file, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);  //shi fou fanhui data suolue tu shu ju

//        intent.putExtra("return-data", false);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
//                new File(getExternalCacheDir(), "face-cropped")));
        startActivityForResult(intent, CROP_RES);
    }
}
