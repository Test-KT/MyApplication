package com.lsl.pathcicle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    PathCircle mCirclel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCirclel = (PathCircle) findViewById(R.id.test);
    }


    public void doClick(View v) {
        mCirclel.startAnimation();
    }
}
