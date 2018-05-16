package com.example.liangxiao.kotlinpractice

import com.google.gson.annotations.SerializedName

/**
 * Created by liangxiao on 2018/5/15.
 */
data class StatusData(val created_at: String, val text: String, @SerializedName("reposts_count") val shareCount: Int,
                      val comments_count: Int, val attitudes_count: Int, val user: UserData)