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

            // intent로 이동할 때 intent라는 객체 내부에 어떠한 값을 담아서 넘겨줄 수 있다.
            // putExtra(특정 값을 넘기기전에 그 값을 구분할 만한 key값, 실제로 넘길 객체)
            intent.putExtra("msg", binding.tvSendMsg.text.toString()) // HelloWorld라는 텍스트 값을 담은 뒤 msg라는 키로 잠궜다.

            startActivity(intent) // intent에 저장되어있는 엑티비티 쪽으로 이동한다.

            finish() // 자기 자신 엑티비티를 파괴한다.
        }

    }
}