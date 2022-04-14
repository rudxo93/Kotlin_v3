package com.example.listviewkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class UserAdapter (val context: Context, val UserList: ArrayList<User>) : BaseAdapter() { // BaseAdapter 상속

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // view를 붙힐때 LayoutInflater를 사용한다.
        // 리스트 뷰의 한 컬럼당 아이템은 만들어준 list_item_user 형태를 따라준다.
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_user, null)

        // User 모델을 따라간다. User 모델대로 차근차근 선언해주면 된다.
        val profile = view.findViewById<ImageView>(R.id.iv_profile) // <뷰의 형태>(id)
        val name = view.findViewById<TextView>(R.id.tv_name)
        val age = view.findViewById<TextView>(R.id.tv_age)
        val greet = view.findViewById<TextView>(R.id.tv_greet)

        // listView가 출력이 될때 getView를 호출하는데 그때 position은 0부터 세는데
        // 그 각각에 따른 position을 가지고 있는 User라는 변수를 만들어서 활용한다.
        val user = UserList[position] // 위에서의 position을 끌어온다.

        // User.kt(모델 객체)와 연결이 된다.
        profile.setImageResource(user.profile) // drawable형태의 경로를 넣어준다.
        name.text = user.name
        age.text = user.age
        greet.text = user.greet

        return view
    }

    override fun getItem(position: Int): Any { // 어떤 아이템들을 끌어올것인지?
        return UserList[position] // ArrayList에 있는 위치들만큼 getItem한다.
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return UserList.size // userList의 사이즈만큼 만들어준다.
    }

}