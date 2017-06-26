package com.hontek.rx2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RxJava2 第二章线程调度学习 学习博客链接：http://www.jianshu.com/p/8818b98c44e2
 */
public class Main2Activity extends AppCompatActivity {
    String tag = "info--->";

    CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mCompositeDisposable = new CompositeDisposable();

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(tag, Thread.currentThread().getName());
                e.onNext(1);
            }
        });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e(tag, value.toString());
                Log.e(tag, Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribe(observer); //subscribeOn 发送事件的线程  observeOn 接收事件的线程


    }


    public void doClick(View v) {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(tag, "first");
                e.onNext(1);
            }
        });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.e(tag, value.toString());
                Log.e(tag, "----------");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(tag, "doOnNext1" + integer);
                Log.e(tag, Thread.currentThread().getName());
            }
        }).observeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.w(tag, "doOnNext2" + integer);
                Log.e(tag, Thread.currentThread().getName());
            }
        }).subscribe(observer);
    }

    /**
     * 仅仅是模拟
     *
     * @return
     */
    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();


    }

    /**
     * 模拟网络请求
     */
    private void twoClick() {
        Api api = create().create(Api.class);
        api.login(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                mCompositeDisposable.add(d); //把水管管理放到一个容器中，使用容器去管理

            }

            @Override
            public void onNext(ResponseBody value) {

            }

            @Override
            public void onError(Throwable e) {
                Log.w(tag, "error");
            }

            @Override
            public void onComplete() {
                Log.w(tag, "success");

            }
        });
    }


    /**
     * 模拟读写数据库
     */
    private Observable<String> readDB() {

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //读取数据库的操作
                e.onNext("数据库信息");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()); //读取数据库信息在ui线程，返回数据操作UI在主线程
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear(); //切断水管，在网络请求过程中，如果界面已经退出了，然后回调更新ui会导致app崩溃，所以需要切断水管（中断接收事件）
    }
}
