package com.hontek.rx2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    private static final String tag = "info--->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
//                e.onError(new RuntimeException("sss"));  //不能连续发送两个错误事件
//                e.onError(new RuntimeException("sss2")); //会导致崩溃

                e.onNext(3);
                e.onComplete(); //发送完成事件后还可以发送事件，并且可以多次调用，但是接收事件收到完成事件后就不在接收事件了，并且跟随后面的事件也停止发送
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                 mDisposable=d;
            }

            @Override
            public void onNext(Integer value) {
                Log.e(tag, value.toString());
                if(value.equals(2)){
                    Log.d(tag,"disable");
                    mDisposable.dispose();  //简单理解为切断下游水管（上游还在继续发送事件过来，但是下游已经不接收事件了）
                    Log.d(tag, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag, e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.e(tag, "finsh");
            }
        });


    }
}
