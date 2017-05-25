package com.hontek.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SimpleLine mSimpleLine;
    SimpleLineView mSimpleLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSimpleLine = (SimpleLine) findViewById(R.id.simpleline);

        mSimpleLineView = (SimpleLineView) findViewById(R.id.simpleview);


        String[] xItem = {"1", "2", "3", "4", "5", "6", "7"};
        String[] yItem = {"10k", "20k", "30k", "40k", "50k"};
        if (mSimpleLine == null)
            Log.e("wing", "null!!!!");
        mSimpleLine.setXItem(xItem);
        mSimpleLine.setYItem(yItem);
        HashMap<Integer, Integer> pointMap = new HashMap();
        for (int i = 0; i < xItem.length; i++) {
            pointMap.put(i, (int) (Math.random() * 5));
        }
        mSimpleLine.setData(pointMap);


        mSimpleLineView.setXItem(xItem);
        mSimpleLineView.setYItem(yItem);
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < xItem.length; i++) {
            map.put(i, (int) (Math.random() * 300));
        }
        mSimpleLineView.setData(map);
    }
}
