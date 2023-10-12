package com.example.learnKotlin.chapter8

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_read_system_contact.*

//读取系统通讯录
class ReadSystemContactActivity : AppCompatActivity() {

    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_system_contact)

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,contactsList)
        contactsView.adapter = adapter
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts()
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun readContacts(){
        // 查询联系人数据
        //ContactsContract.CommonDataKinds.Phone类已经帮我们做好了封装，提供了一个CONTENT_URI常量
        contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                             null,null,null,null)?.apply {
            while (moveToNext()){
                // 获取联系人姓名
                val displayName = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                // 获取联系人手机号
                val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add("$displayName\n$number")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }
}