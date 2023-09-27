package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        button1.setOnClickListener {
            /*显式意图*/
            val intent = Intent(this,SecondActivity::class.java)
            /*隐式意图*/
            //val intent = Intent("com.example.activitytest.ACTION_START")
            //intent.addCategory("com.example.activitytest.MY_CATEGORY")
            /*隐式意图启动其他程序*/
            /*val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com")*/
            /*隐式意图打开系统拨号界面*/
            /*val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")*/
            //startActivity(intent)

            //启动Activity 的最佳写法
            SecondActivity.actionStart(this,"data1","data2")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add_item ->  Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}