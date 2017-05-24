package com.hontek.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SimpleLine mSimpleLine;
    String x[] = {"123", "123", "123", "123", "123"};
    String y[] = {"123", "123", "123", "123", "123"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleLine = (SimpleLine) findViewById(R.id.simpleline);
        mSimpleLine.setXItems(x);
        mSimpleLine.setYItems(y);

    }
}
