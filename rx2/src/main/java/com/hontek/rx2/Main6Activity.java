package com.hontek.rx2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * flowable使用教程，学习完毕啦  rxjava2 第7，8章学习   博客链接：http://www.jianshu.com/p/a75ecf461e02
 */
public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Flowable<Integer> flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
//                for (int i = 0; ; i++) {
//                    e.onNext(i);
//                }
                e.onNext(1);
                Log.e("info--->", "1");
                e.onNext(2);
                Log.e("info--->", "2");
                e.onNext(3);
                Log.e("info--->", "3");
                e.onComplete();
                Log.e("info--->", "onComplete");
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(2);
            }

            @Override
            public void onNext(Integer integer) {
                Log.w("info-->", "onNext" + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("info-->", t.toString());
            }

            @Override
            public void onComplete() {

            }
        };
        flowable.subscribe(subscriber);

    }


}
