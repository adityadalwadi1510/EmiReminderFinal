package com.example.emireminderfinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.emireminderfinal.fragment.DisplayTask.CompletedTask
import com.example.emireminderfinal.fragment.DisplayTask.PendingTask
import com.example.emireminderfinal.fragment.DisplayTask.ViewPagerAdpater
import kotlinx.android.synthetic.main.activity_all_task.*

class AllTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_task)

        setUpTabs()

    }

    private fun setUpTabs() {
        val adpater = ViewPagerAdpater(supportFragmentManager)
        adpater.addFragment(CompletedTask(), "Completed")
        adpater.addFragment(PendingTask(), "Pending")

        viewPager.adapter = adpater

        tabLayout.setupWithViewPager(viewPager)


    }
}