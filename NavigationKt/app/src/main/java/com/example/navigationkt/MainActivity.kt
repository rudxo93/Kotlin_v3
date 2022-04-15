package com.example.navigationkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.navigationkt.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnNavi.setOnClickListener {
            binding.layoutDrawer.openDrawer(GravityCompat.START) // START - left / END - right 와 같은말이다.
        }

        binding.naviView.setNavigationItemSelectedListener(this) // 네이게이션 메뉴 아이템에 클릭 속성 부여

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean { // 네이게이션 메뉴 아이템 클릭 시 수행
        when(item.itemId){
            R.id.access -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "이메일", Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "메시지", Toast.LENGTH_SHORT).show()
        }
        binding.layoutDrawer.closeDrawers() // 토스트 메시지를 띄우면서 현재 펼쳐저 네이게이션 뷰를 닫는다.
        return false
    }

    // 앱에서 back버튼 action에 분기처리로 나눠주어야 한다.
    override fun onBackPressed() { // back버튼 누를 시 수행하는 메소드

        if(binding.layoutDrawer.isDrawerOpen(GravityCompat.START)){ // 만약 layoutDrawer가 open되어 있으면 close해준다.
            binding.layoutDrawer.closeDrawers() //
        } else {
            super.onBackPressed() // 일반 back버튼 기능 실행(finish)
        }
    }
}