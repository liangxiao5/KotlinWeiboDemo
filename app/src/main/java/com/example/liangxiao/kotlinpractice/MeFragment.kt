package com.example.liangxiao.kotlinpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.timeline_item.view.*
import kotlinx.android.synthetic.main.usershow_fragment.*

class MeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.usershow_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_weibo.layoutManager = LinearLayoutManager(context)

        Api.getUserInfomation().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val controller  = Fresco.newDraweeControllerBuilder()
                            .setImageRequest(ImageRequest.fromUri(it.profile_image_url))
                            .build()
                    me_icon.controller = controller
                    nick_name.text = it.name
                    user_description.text = it.description
                    statuses_count.text = it.statuses_count.toString()
                    follow_count.text = it.friends_count.toString()
                    followers_count.text = it.followers_count.toString()
                })
        Api.getUserTimeLine().getUserTimeLine()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({userTimeLine->
                    user_weibo.adapter = TimeLineAdapter(userTimeLine.statuses)
                })
    }

}