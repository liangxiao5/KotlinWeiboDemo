package com.example.liangxiao.kotlinpractice

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserShowService {
    @GET("users/show.json")
    fun getUserInfo(@Query("uid") uid:Int,@Query("access_token") token:String = Api.accessToken.token):Observable<UserData>
}