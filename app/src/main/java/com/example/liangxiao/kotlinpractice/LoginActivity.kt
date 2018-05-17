package com.example.liangxiao.kotlinpractice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.sina.weibo.sdk.auth.AccessTokenKeeper
import com.sina.weibo.sdk.auth.Oauth2AccessToken
import com.sina.weibo.sdk.auth.WbAuthListener
import com.sina.weibo.sdk.auth.WbConnectErrorMessage
import com.sina.weibo.sdk.auth.sso.SsoHandler
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by liangxiao on 2018/5/15.
 */
class LoginActivity: AppCompatActivity() {
    var ssoHandler = SsoHandler(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn.setOnClickListener(
        {
            ssoHandler.authorizeClientSso(object: WbAuthListener{
                override fun cancel() {
                    Toast.makeText(this@LoginActivity,"cancel",Toast.LENGTH_SHORT).show()

                }

                override fun onSuccess(token: Oauth2AccessToken) {
                    Api.accessToken = token
                    if (Api.accessToken.isSessionValid){
                        AccessTokenKeeper.writeAccessToken(this@LoginActivity,Api.accessToken)
                        Toast.makeText(this@LoginActivity,"token 保存成功",Toast.LENGTH_SHORT).show()
                        jumpToMain()
                    }

                }

                override fun onFailure(message: WbConnectErrorMessage) {
                    if (message.errorCode == "8000"){
                        Toast.makeText(this@LoginActivity,"未安装客户端 ",Toast.LENGTH_SHORT).show()
                        ssoHandler.authorizeWeb(this)
                    }else{

                        Toast.makeText(this@LoginActivity,"failure${message.errorCode} " + message.errorMessage,Toast.LENGTH_SHORT).show()
                    }



                }
            })
        })

        val token = AccessTokenKeeper.readAccessToken(this)
        if (token != null && token.isSessionValid && token.expiresTime * 1000 < System.currentTimeMillis()){
            Api.accessToken = token
            jumpToMain()

        }


    }

    private fun jumpToMain() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}