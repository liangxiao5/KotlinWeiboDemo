package com.example.liangxiao.kotlinpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest

class MeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.usershow_item,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

    class UserInfoAdapter(val userInfo:List<UserData>):RecyclerView.Adapter<MeFragment.UserInfoViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeFragment.UserInfoViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.usershow_item,parent,false)
            return MeFragment.UserInfoViewHolder(view)
        }

        override fun onBindViewHolder(holder:UserInfoViewHolder, position: Int) {
            val info = userInfo[position]
            holder.apply {
                val controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(ImageRequest.fromUri(info.profile_image_url))
                        .build()
                meIcon.controller = controller
                meNickName.text = info.name
                meDescription.text = info.description
                meStatusesCount.text = info.statuses_count.toString()
                meFollowCount.text = info.friends_count.toString()
                meFollowerCount.text = info.followers_count.toString()
            }
        }

        override fun getItemCount(): Int {
            return userInfo.size

        }
    }

    class UserInfoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val meIcon:SimpleDraweeView = view.findViewById(R.id.me_icon)
        val meNickName:TextView = view.findViewById(R.id.nick_name)
        val meDescription:TextView = view.findViewById(R.id.user_description)
        val meStatusesCount:TextView = view.findViewById(R.id.statuses_count)
        val meFollowCount:TextView = view.findViewById(R.id.follow_count)
        val meFollowerCount:TextView = view.findViewById(R.id.followers_count)
    }
}