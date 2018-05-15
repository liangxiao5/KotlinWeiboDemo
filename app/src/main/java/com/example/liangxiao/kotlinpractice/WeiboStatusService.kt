package com.example.liangxiao.kotlinpractice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by liangxiao on 2018/5/15.
 */
interface WeiboStatusService {
    @GET("statuses/home_timeline.json")
    fun getTimeLine(@Query("access_token") token:String = Api.accessToken.token):Observable<TimeLine>
}