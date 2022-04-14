package com.example.textviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.textviewkt.databinding.ActivityMainBinding

/*
  kt파일
  - 코틀린 파일에서는 동적인 역할을 담당한다.
    정적인 화면을 통해서 setContentView구문을 사용해서 layout에 있는 activity_main.xml을
    코틀린 파일에 연결을 해주는 것이다.
    xml과 kt는 한 쌍이다.
    그래서 코틀린 파일에서 xml의 textView를 조종할 수 있다.
    이때 조종을 할 수 있는 기준은 id값이다.
    id값을 가지고 있다고 하면 언제든지 조종이 가능하다.
*/

class MainActivity : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private var mBinding : ActivityMainBinding? = null

    // 매번 null체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) { // 앱이 최초 실행되었을때 수행.
        super.onCreate(savedInstanceState)

        // 기존 setContentView를 제거..
        //setContentView(R.layout.activity_main)

        // 자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        // 엑티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의 인스턴스를 활용하여
        // 생성된 뷰를 엑티비티에 표시한다.
        setContentView(binding.root)

        // 이제부터 binding 바인딩 변수를 활용하여 마음 껏 xml 파일 내의 뷰 id 접근이 가능해집니다.
        // 뷰 id도 파스칼케이스 + 카멜케이스의 네이밍규칙 적용으로 인해서 tv_title -> tvTitle 로 자동 변환 되었습니다.
        binding.tvTitle.setText("안녕하세요 변경된 text입니다.")

    }

    override fun onDestroy() {
        // onDestroy에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}