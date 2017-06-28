package com.hontek.rx2;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface Api {
    @GET
    Observable<ResponseBody> login(@Body RequestBody request);
//
    @GET
    Observable<ResponseBody> register(@Body RequestBody request);
}