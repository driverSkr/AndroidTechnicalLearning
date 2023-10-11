package com.example.learnKotlin.chapter8

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_runtime_permissions.*

//运行时权限申请：打电话
class RuntimePermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_runtime_permissions)

        makeCall.setOnClickListener{
            //判断用户是不是已经给过我们授权了
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //没有权限的情况，申请权限
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE),1)
            } else {
                call()
            }
        }
    }

    //授权的结果则会封装在grantResults参数当中
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun call(){
        try {
            val intent = Intent(Intent.ACTION_CALL)
            //指定了协议是tel ，号码是10086
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException){
            e.printStackTrace()
        }
    }
}