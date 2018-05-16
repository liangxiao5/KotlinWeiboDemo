package com.example.liangxiao.kotlinpractice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by liangxiao on 2018/5/17.
 */
interface SearchService {
    @GET("search/topics.json")
    fun getSearchResult(@Query("q")q:String,@Query("access_token")token:String = Api.accessToken.token): Observable<TimeLine>
}