package com.example.sharedkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) { // 엑티비티가 처음 실행될 때 한번 수행하는 곳(초기화)
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: 저장된 데이터를 로드
        loadData() // edit text 저장되어있던 값을 setText
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        // 1번째 인자에서는 저장할 당시의 key값을 적어준다, 2번째는 key값에 데이터가 존재하지 않을 경우 대체 값을 적어준다.ㅁㄴㅇ
        binding.etHello.setText(pref.getString("name", ""))
    }

    private fun saveData() {
        // pref라는 name의 파일로 앱 내부의 폴더 경로에 pref로 저장한다 / 0은 저장옵션이다.(보통 0)
        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit() // 수정모드
        edit.putString("name", binding.etHello.text.toString()) // String형태로 담는다. 1번째 인자에는 key값을, 2번째 인자에는 실제 담아줄 값
        edit.apply() // 최종으로 값을 저장 완료
    }

    override fun onDestroy() { // 엑티비티의 생명주기에서 해당 엑티비티가 종료되는 시점이 다가올 때 호출
        super.onDestroy()

        saveData() // edit text값을 저장
    }

}