package com.example.logkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    var a : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a = 2

        if (a == 2) {
            Log.d("if 문", "ENTER")
         } else if (a == 1){
            Log.d("else if 문", "ENTER")
        }

        //Log.d("var a의 현재 변수 값", a.toString())
        //Log.d("로그테스트", "Ok")

    }
}