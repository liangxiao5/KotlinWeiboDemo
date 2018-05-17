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
import kotlinx.android.synthetic.main.timeline_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class TimelineFragment: Fragment() {
    companion object {
        val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater?.inflate(R.layout.timeline_fragment,container,false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        content_list.layoutManager = LinearLayoutManager(context)

        Api.getStatusService()
                .getTimeLine()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { timeLine ->
                    content_list.adapter = TimeLineAdapter(timeLine.statuses)
                    println("收到${timeLine.total_number}条weibo")
                }
    }


//    class StatusAdapter(private val statuses: List<StatusData>): RecyclerView.Adapter<StatusViewHolder>(){
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
//            val  view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_item,parent,false)
//            return StatusViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
//            val data = statuses[position]
//            holder.apply {
//                content.text = data.text
//                userName.text = data.user.name
//                val controller  = Fresco.newDraweeControllerBuilder()
//                        .setImageRequest(ImageRequest.fromUri(data.user.profile_image_url))
//                        .build()
//                userIcon.controller = controller
//                textTime.text = DATE_FORMAT.format(Date(data.created_at))
//                shareCount.text = data.shareCount.toString()
//                commentsCount.text = data.comments_count.toString()
//                likeCount.text = data.attitudes_count.toString()
//            }
//
//        }
//
//        override fun getItemCount(): Int {
//            return statuses.size
//        }
//
//    }
//
//    class StatusViewHolder(view: View): RecyclerView.ViewHolder(view){
//        val content: TextView = view.findViewById(R.id.content)
//        val userName:TextView = view.findViewById(R.id.user_name)
//        val userIcon: SimpleDraweeView = view.findViewById(R.id.user_icon)
//        val textTime:TextView = view.findViewById(R.id.text_time)
//        val shareCount:TextView = view.findViewById(R.id.share_count)
//        val commentsCount:TextView = view.findViewById(R.id.comments_count)
//        val likeCount:TextView = view.findViewById(R.id.like_count)
//
//    }
}