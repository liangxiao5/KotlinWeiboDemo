package com.example.liangxiao.kotlinpractice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by liangxiao on 2018/5/14.
 */
interface MovieService {
    @GET("top250")
    fun getMovie(@Query("start")start:Int,@Query("count")count:Int ) :Observable<Data>
}