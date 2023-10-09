package com.example.learnKotlin.chapter7

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Kotlin.R
import kotlinx.android.synthetic.main.activity_file_storage_read.*
import java.io.*

//文件存储（读写文件到/data/data/<package name>/files/目录下）
class FileStorageReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_storage_read)

        //重新启动程序时EditText 中能够保留我们上次输入的内容
        val inputText = load()
        if (inputText.isNotEmpty()){
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restoring succeeded",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = editText.text.toString()
        save(inputText)
    }

    //将数据存储到文件中(openFileOutput)
    private fun save(inputText: String){
        try {
            val output = openFileOutput("data",Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            //use函数，这是Kotlin 提供的一个内置扩展函数。
            // 它会保证在Lambda 表达式中的代码全部执行完之后自动将外层的流关闭，这样就不需要我们再编写一个finally语句，手动去关闭流
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    //从文件中读取数据(openFileInput)
    private fun load(): String{
        val content = StringBuffer()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                //forEachLine函数，这也是Kotlin提供的一个内置扩展函数，它会将读到的每行内容都回调到Lambda表达式中
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}