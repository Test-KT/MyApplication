package com.hontek.rx2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * rxjava2 第四章学习 zip操作符 博客链接http://www.jianshu.com/p/bb58571cdb64
 * zip操作符使用场景：比如一个界面需要展示用户的一些信息, 而这些信息分别要从两个服务器接口中获取, 而只有当两个都获取到了之后才能进行展示, 这个时候就可以用Zip了:
 */
public class Main4Activity extends AppCompatActivity {

    /**
     * 出现这个报错是不是长数据流所在的线程正在结束但还没有结束,而在sleep的过程中线程被结束了所以报错 ,rxjava2的一个bug
     */
    static {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof InterruptedException) {
                    Log.e("info-->", "线程bug了");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

    }


    public void doZip(View v) {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext(1);
                Log.e("info-->", "1");
                Thread.sleep(1000);
                e.onNext(2);
                Log.e("info-->", "2");
                Thread.sleep(1000);
                e.onNext(3);
                Log.e("info-->", "3");
                Thread.sleep(1000);
                e.onNext(4);
                Log.e("info-->", "4");
                Thread.sleep(1000);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());


        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("A");
                Log.e("info-->", "A");
                Thread.sleep(1000);
                e.onNext("B");
                Log.e("info-->", "B");
                Thread.sleep(1000);
                e.onNext("C");
                Log.e("info-->", "C");
                Thread.sleep(1000);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());


        Observable.zip(observable2, observable1, new BiFunction<String, Integer, String>() {

            @Override
            public String apply(String integer, Integer s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("info--->", s);
            }
        });
    }
}
