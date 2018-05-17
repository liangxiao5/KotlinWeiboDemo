package com.example.liangxiao.kotlinpractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val map = mapOf(
            R.id.timeline to TimelineFragment(),
            R.id.message to MessageFragment(),
            R.id.discover to SearchFragment(),
            R.id.person to MeFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {
            val fragment = map[it.itemId]
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            true
        }
        navigation.selectedItemId = R.id.timeline
    }


}
