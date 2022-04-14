package com.example.intentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnA.setOnClickListener{
            // var : 변수 / val : 자바에서는 final 값이 변경되지 못하는 변수(상수)
            val intent = Intent(this,SubActivity::class.java) // 다음 화면으로 이동하기 위한 Intent객체 생성

            startActivity(intent)
        }
    }
}