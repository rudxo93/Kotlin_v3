package com.example.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.listviewkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) { // 엑티비티의 시작지점
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // String형태의 배열 생성
        val item = arrayOf("사과", "배", "딸기", "파인애플", "안드로이드")
        // adapter = 코드 연결할 때 플러그 선을 연결하듯이 전원을 연결하는 개념
        // listView에는 adapter라는 것이 연결이 되어 있어야지만 데이터들을 직접 input을 넣어줄 수 있다.
        // listView는 항상 adapter를 생성해줘야지만 사용할 수 있다!!!!
        // context란 한 엑티비티의 모든 정보를 담고있다.
        binding.listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)
        
    }
}