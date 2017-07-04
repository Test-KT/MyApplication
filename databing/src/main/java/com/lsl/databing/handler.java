package com.lsl.databing;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class handler{
        public void onclickDP(View v){
            Toast.makeText(v.getContext(),"我被点击啦",Toast.LENGTH_SHORT).show();
            Log.e("info-->","我被点击啦");
        }


    public void showDP(String str){
        Log.e("info-->",str+"被点击啦");
    }

    public void showCheck(boolean ischeck){
        Log.e("info-->",ischeck+"被点击啦");
    }

    public void showIsVie(boolean ischeck){
        Log.e("info-->",ischeck+"----------->");
    }


}