package com.example.liangxiao.kotlinpractice

import com.sina.weibo.sdk.auth.Oauth2AccessToken
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by liangxiao on 2018/5/15.
 */
object Api {
    const val baseUrl = "https://api.weibo.com/2/"
    var accessToken = Oauth2AccessToken()
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
    fun getMovieService() :MovieService{
        return retrofit.create(MovieService::class.java)
    }

    fun getStatusService(): WeiboStatusService {
        return retrofit.create(WeiboStatusService::class.java)
    }

}