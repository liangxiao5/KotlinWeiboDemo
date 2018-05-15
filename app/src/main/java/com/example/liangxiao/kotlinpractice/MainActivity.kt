package com.example.liangxiao.kotlinpractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val baseUrl = "https://api.douban.com/v2/movie/" ///top250?start=0&count=5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn.setOnClickListener({ v ->
//           movie()
//        })
        list.layoutManager = LinearLayoutManager(this)


        Api.getStatusService()
                .getTimeLine()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { timeLine ->
                    list.adapter = StatusAdapter(timeLine.statuses)
                    println("收到${timeLine.total_number}条weibo")
                }
    }

    fun test() {
        var list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Flowable.fromIterable(list)
                .filter {
                    it > 5}
                .map { "title$it" }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { println(it) }

    }
    fun movie(){
        Api.getMovieService()
                .getMovie(0,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println("电影共有${it.total}部")
                }


    }

    class StatusAdapter(private val statuses: List<StatusData>):RecyclerView.Adapter<StatusViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
            val  view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_item,parent,false)
            return StatusViewHolder(view)
        }

        override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
            val data = statuses[position]
            holder.textView.text = data.text

        }

        override fun getItemCount(): Int {
            return statuses.size
        }

    }

    class StatusViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView:TextView = view.findViewById(R.id.timeline_item)

    }
}
