package com.example.edittextbuttonkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edittextbuttonkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) { // activity가 최초 실행되면 이곳을 수행한다.
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGetText.setOnClickListener{ // EditText에 입력되어있는 값을 가지고 와서 textView에 뿌려준다.
            // EditText에 입력되어 text값을 String형태로 변환해서 가져온다. EditText에 입력되어 있는 값
            var resultText = binding.etText.text.toString()
            // 입력된 값을 textView에 set해준다.
            binding.tvResult.setText(resultText)

        }



    }
}