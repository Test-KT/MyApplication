package com.lsl.mvvm;

import android.util.Log;
import android.view.View;

public class MyHandlers {
    public void onClickFriend(View view, String name) {
        Log.e("info---->", name + "点击了我");

    }


    public String parseStr(String name) {

        return "i love " + name;
    }
}