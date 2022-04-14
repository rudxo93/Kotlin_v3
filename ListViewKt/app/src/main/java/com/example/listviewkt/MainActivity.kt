package com.example.listviewkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.listviewkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null

    private val binding get() = mBinding!!

    var UserList = arrayListOf<User>(
        User(R.drawable.android, "안드로이드", "29", "안드로이드입니다"), // 모델객체의 순서에 맞게 적어주면 된다.
        User(R.drawable.android, "자바", "24", "자바입니다"),
        User(R.drawable.android, "스프링", "35", "스프링입니다"),
        User(R.drawable.android, "스프링부트", "27", "스프링부트입니다"),
        User(R.drawable.android, "코틀린", "24", "코틀린입니다"),
        User(R.drawable.android, "IOS", "35", "IOS입니다"),
        User(R.drawable.android, "자바스크립트", "13", "자바스크립트입니다"),
        User(R.drawable.android, "vue.js", "24", "뷰입니다"),
        User(R.drawable.android, "Node.js", "28", "노드입니다"),
        User(R.drawable.android, "HTML", "11", "HTML입니다")
    )

    override fun onCreate(savedInstanceState: Bundle?) { // 엑티비티의 시작지점
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /*
        // String형태의 배열 생성
        val item = arrayOf("사과", "배", "딸기", "파인애플", "안드로이드")

        //adapter = 코드 연결할 때 플러그 선을 연결하듯이 전원을 연결하는 개념
        //listView에는 adapter라는 것이 연결이 되어 있어야지만 데이터들을 직접 input을 넣어줄 수 있다.
        //listView는 항상 adapter를 생성해줘야지만 사용할 수 있다!!!!
        //context란 한 엑티비티의 모든 정보를 담고있다.

        binding.listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)*/

        // main에서 UserAdapter로 쏘면 UserAdapter에서 UserList를 받아서 User라는 값이 할당이 되고 그때
        // 이용해서 뿌려주는 방식이다.
        val Adapter = UserAdapter(this, UserList)
        binding.listView.adapter = Adapter // 이렇게 해주면 UserAdapter라는 custom을 만든다.

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            // position 위치로부터 item을 가져온다.
            val selectItem = parent.getItemAtPosition(position) as User // 현재 클릭한 position을 가지고있는 변수가 만들어진다.
            // Toast를 띄우는데 this 현재 엑티비티로 부터, selectItem.name은 User 모델의 name을 출력한다, 짧게 띄워준다.
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()

        }
    }
}