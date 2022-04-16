package com.example.recyclerviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileList = arrayListOf(
            Profiles(R.drawable.man, "김드로이드", 29, "안드로이드 앱 개발자"),
            Profiles(R.drawable.woman, "이드로이드", 14, "아이폰 앱 개발자"),
            Profiles(R.drawable.man, "박드로이드", 35, "리엑트 앱 개발자"),
            Profiles(R.drawable.man, "조드로이드", 24, "플러터 앱 개발자"),
            Profiles(R.drawable.woman, "홍드로이드", 36, "유니티 앱 개발자"),
            Profiles(R.drawable.man, "정드로이드", 22, "알고리즘 앱 개발자"),
            Profiles(R.drawable.man, "나드로이드", 13, "하이브리드 앱 개발자"),
            Profiles(R.drawable.woman, "윤드로이드", 15, "그냥 앱 개발자"),
            Profiles(R.drawable.woman, "강드로이드", 27, "심심한 앱 개발자"),
            Profiles(R.drawable.man, "공드로이드", 42, "즐거운 앱 개발자"),
            Profiles(R.drawable.man, "콩드로이드", 24, "쓸쓸한 앱 개발자"),
            Profiles(R.drawable.woman, "항드로이드", 15, "외로운 앱 개발자"),
            Profiles(R.drawable.man, "형드로이드", 11, "신나는 앱 개발자"),
            Profiles(R.drawable.man, "긱드로이드", 18, "복잡한 앱 개발자"),
            Profiles(R.drawable.woman, "락드로이드", 39, "어려운 앱 개발자")
        )

        binding.rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvProfile.setHasFixedSize(true) // 리사이클러뷰의 성능 개선방안

        binding.rvProfile.adapter = ProfileAdapter(profileList)

    }
}