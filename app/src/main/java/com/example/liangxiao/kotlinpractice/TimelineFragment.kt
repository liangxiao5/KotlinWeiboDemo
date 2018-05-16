package com.example.liangxiao.kotlinpractice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TimelineFragment: Fragment() {

    val TimelineFragment by lazy { TimelineFragment() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val  view = inflater?.inflate(R.layout.home_page_fragment,container,false)
        return view
    }
}