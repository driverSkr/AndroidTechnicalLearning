package com.example.broadcastReceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

//登录模拟
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText accountEdit = findViewById(R.id.accountEdit);
        EditText passwordEdit = findViewById(R.id.passwordEdit);

        findViewById(R.id.login).setOnClickListener(v -> {
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            //如果账号是admin且密码是123456，就认为登陆成功
            if (account.equals("admin") && password.equals("123456")){
                Intent intent = new Intent(this,ForcedDownLineActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}