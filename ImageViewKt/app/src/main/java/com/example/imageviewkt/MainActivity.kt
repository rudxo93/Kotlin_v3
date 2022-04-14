package com.example.imageviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imageviewkt.databinding.ActivityMainBinding

 // toast메시지는 버튼을 클릭했을 때 본인이 원하는 메시지를 하단에 이쁘게 띄워주는 것

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnToast.setOnClickListener{

            binding.ivProfile.setImageResource(R.drawable.robot) // ImageView에 새로운 이미지 등록
            Toast.makeText(this@MainActivity, "버튼이 클릭 되었습니다!", Toast.LENGTH_SHORT).show()

        }



    }
}