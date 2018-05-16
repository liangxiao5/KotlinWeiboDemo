package com.example.liangxiao.kotlinpractice

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.sina.weibo.sdk.WbSdk
import com.sina.weibo.sdk.auth.AuthInfo

/**
 * Created by liangxiao on 2018/5/15.
 */
class App: Application() {
    val SCOPE ="email,direct_messages_read,direct_messages_write," +
            "friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
            "follow_app_official_microblog," + "invitation_write"
    override fun onCreate() {
        super.onCreate()
        var authInfo = AuthInfo(this,"1663491697","https://api.weibo.com/oauth2/default.html",SCOPE)
        WbSdk.install(this,authInfo)
        Fresco.initialize(this)
    }
}