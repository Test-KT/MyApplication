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
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {
    Disposable mDisposable;
    String tag = "info--->";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        }).map(new Function<Integer, String>() {  //map操作符可以对数据进行转换

            @Override
            public String apply(Integer integer) throws Exception {
                return "this is " + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e(tag, value);

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        });


    }


    public void doSomth(View v) {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(100);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                String str = "i am " + integer;
                return Observable.just(str);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.w(tag, s);
            }
        });
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
     * 使用flatmap模拟嵌套网络请求
     */
    public void doNet() {
        final Api api = create().create(Api.class);
        api.register(null) //发起注册请求
                .subscribeOn(Schedulers.io()) //请求在io线程
                .observeOn(AndroidSchedulers.mainThread()) //处理结果在UI线程
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable dis) throws Exception {
                        mDisposable=dis;
                    }
                })
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        //注册完毕后的响应
                        //通过抛异常来中断登录操作，如果注册失败的话 比较优雅的可以使用Disposable去中断
                        if(responseBody.string().isEmpty()){
                            if(mDisposable.isDisposed()) {
                                mDisposable.dispose();
                            }
                        }
                    }
                }).observeOn(Schedulers.io()) //切换到UI线程进行登录请求

                .flatMap(new Function<ResponseBody, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(ResponseBody responseBody) throws Exception {
                        return api.login(null);
                    }
                }).observeOn(AndroidSchedulers.mainThread()) //在ui线程处理结果
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {  //登录成功

                    }
                }, new Consumer<Throwable>() {  //登录失败
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }

}
