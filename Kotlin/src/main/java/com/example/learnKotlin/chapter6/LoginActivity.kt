package com.example.learnKotlin.chapter6

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_login.*

//强制下线案例
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123456"){
                val intent = Intent(this,ForcedDownlineActivity::class.java)
                startActivity(intent)
                finish()
            } else{
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}