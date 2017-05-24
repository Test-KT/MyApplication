package com.hontek.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SimpleLine mSimpleLine;
    String x[] = {"0", "1", "2", "3", "4"};
    String y[] = {"4", "3", "2", "1", "0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleLine = (SimpleLine) findViewById(R.id.simpleline);
        mSimpleLine.setXItems(x);
        mSimpleLine.setYItems(y);

    }
}
