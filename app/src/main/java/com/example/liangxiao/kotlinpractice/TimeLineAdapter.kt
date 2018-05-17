package com.example.liangxiao.kotlinpractice

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import java.util.*

/**
 * Created by liangxiao on 2018/5/17.
 */
class TimeLineAdapter (private val statuses: List<StatusData>): RecyclerView.Adapter<StatusViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_item,parent,false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val data = statuses[position]
        holder.apply {
            content.text = data.text
            userName.text = data.user.name
            val controller  = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(data.user.profile_image_url))
                    .build()
            userIcon.controller = controller
            textTime.text = TimelineFragment.DATE_FORMAT.format(Date(data.created_at))
            shareCount.text = data.shareCount.toString()
            commentsCount.text = data.comments_count.toString()
            likeCount.text = data.attitudes_count.toString()
        }

    }

    override fun getItemCount(): Int {
        return statuses.size
    }

}

class StatusViewHolder(view: View): RecyclerView.ViewHolder(view){
    val content: TextView = view.findViewById(R.id.content)
    val userName: TextView = view.findViewById(R.id.user_name)
    val userIcon: SimpleDraweeView = view.findViewById(R.id.user_icon)
    val textTime: TextView = view.findViewById(R.id.text_time)
    val shareCount: TextView = view.findViewById(R.id.share_count)
    val commentsCount: TextView = view.findViewById(R.id.comments_count)
    val likeCount: TextView = view.findViewById(R.id.like_count)

}