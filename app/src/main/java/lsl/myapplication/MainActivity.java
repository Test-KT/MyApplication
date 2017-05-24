package lsl.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import lsl.myapplication.loading.CustomLoading;

public class MainActivity extends AppCompatActivity {

    CustomLoading mCustomLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CustomView customView= (CustomView) findViewById(R.id.test);
//        PieData pieData1=new PieData();
//        pieData1.setPercentage(0.2f);
//
//        PieData pieData2=new PieData();
//        pieData2.setPercentage(0.4f);
//
//
//        PieData pieData3=new PieData();
//        pieData3.setPercentage(0.1f);
//
//        PieData pieData4=new PieData();
//        pieData4.setPercentage(0.3f);
//
//
//        List<PieData> pieDatas=new ArrayList<>();
//        pieDatas.add(pieData1);
//        pieDatas.add(pieData2);
//        pieDatas.add(pieData3);
//        pieDatas.add(pieData4);
//
//        customView.setDatas(pieDatas);


        mCustomLoading = (CustomLoading) findViewById(R.id.loading);

    }


    public void doClick(View v) {
        mCustomLoading.setProcess();
    }


}
