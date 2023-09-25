package com.example.contentProvider.AccessOtherApps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.contentProvider.R;

import java.util.ArrayList;

//读取系统联系人
public class ReadSystemContactActivity extends AppCompatActivity {

    private ArrayList<String> contactsList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView contactsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_system_contact);

        contactsView = findViewById(R.id.contactsView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else {
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                readContacts();
            }else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("Range")
    private void readContacts(){
        //ContactsContract.CommonDataKinds.Phone类已经帮我们做好了封装，提供了一个CONTENT_URI常量，而这个常量就是使用Uri.parse()方法解析出来的结果
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String displayName = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName + "\n" + number);
            }
            adapter.notifyDataSetChanged();
            cursor.close();
        }
    }
}