package com.lsl.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lsl.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.city = "Gp";
        user.name = "Gy";
        user.sex = "女";
        user.id = 1;
        user.url="http://onmtj6n3g.bkt.clouddn.com/head.png";
        binding.setUser(user);
        binding.setHandles(new MyHandlers());
    }
}
