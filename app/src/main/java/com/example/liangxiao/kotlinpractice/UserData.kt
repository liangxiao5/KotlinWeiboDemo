package com.example.liangxiao.kotlinpractice

/**
 * Created by liangxiao on 2018/5/16.
 */
data class UserData(val profile_image_url: String, val name: String,
                    val description:String,val statuses_count:Int,
                    val friends_count:Int,val followers_count:Int)