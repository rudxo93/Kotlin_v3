package com.example.tablayout_viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES // 3개의 페이지를 갖는다.

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            // newInstance함수를 이용해서 각 페이지를 만들때마다 String 파라미터를 2개를 전달한다.
            0 -> { BaseFragment.newInstance("page 1", "") }
            1 -> { BaseFragment.newInstance("page 2", "") }
            else -> { BaseFragment.newInstance("page 3", "") }
        }
    }

}