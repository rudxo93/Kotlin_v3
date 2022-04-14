package com.example.intentkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intentkt.databinding.ActivityMainBinding
import com.example.intentkt.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    private var mBinding : ActivitySubBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivitySubBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if(intent.hasExtra("msg")){ // intent안에 msg라는 객체가 있다면?
            // Sub Activity에 존재하는 textView에다가 HelloWorld가 넘겨져 온다.
            binding.tvGetMsg.setText(intent.getStringExtra("msg"))
        }
    }
}