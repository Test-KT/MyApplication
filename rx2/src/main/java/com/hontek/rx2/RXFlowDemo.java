package com.hontek.rx2;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:  rxjava2 学习第 8 ，9章 响应式拉取数据，入门学习到此结束 学习博客链接： http://www.jianshu.com/p/36e0f7f43a51
 * Author   :lishoulin
 * Date     :2017/6/30.
 */

public class RXFlowDemo {
    private static Subscription sSubscription;

    public static void main(String args[]) throws InterruptedException {
        System.out.println("启动程序");

        readDemoFile();
        Thread.sleep(10000000);
    }


    static void readDemoFile() {

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                FileReader fileReader = new FileReader("E:\\Star\\MyApplication\\rx2\\src\\main\\java\\com\\hontek\\rx2\\Main2Activity.java");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String str;
                while ((str = bufferedReader.readLine()) != null && !e.isCancelled()) {
                    while ((e.requested() == 0)) {
                        if (e.isCancelled()) {
                            break;
                        }
                    }
                    e.onNext(str);
                }
                bufferedReader.close();
                fileReader.close();
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        sSubscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {

                        System.out.println(s);
                        sSubscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        System.out.println(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
