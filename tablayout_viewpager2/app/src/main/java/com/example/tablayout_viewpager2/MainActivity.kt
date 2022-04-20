package com.example.tablayout_viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.tablayout_viewpager2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tabIcon = listOf(
        R.drawable.ic_baseline_format_list_bulleted_24,
        R.drawable.ic_baseline_map_24,
        R.drawable.ic_baseline_info_24
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // view pager adapter 설정
        binding.viewpager.apply {
            adapter = MyPagerAdapter(context as FragmentActivity)
        }

        // tabLayout과 viewPager2를 연결할때 TabLayoutMediator사용
        // tabLayout의 id, viewPager의 id를 넣어준다.
        // 각 tab에 표시할 text는 title 0, 1, 2로 나오게 해준다.
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = "Title $position"
            tab.setIcon(tabIcon[position])
        }.attach()
    }
}