package com.example.intelligentnavigationcane.GHJ.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelligentnavigationcane.GHJ.The_First.MainActivity;
import com.example.intelligentnavigationcane.R;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button register;
    private EditText userpassword ;
    private EditText username;
    private SQLite mSQlite;
    private User view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register=findViewById(R.id.btn_register);
        login=findViewById(R.id.btn_login);
        username=findViewById(R.id.edit_name);
        userpassword=findViewById(R.id.edit_pass);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mSQlite.getAllDATA();
                    boolean userdata = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);   //可存储账号数量
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            userdata = true;
                            break;
                        } else {
                            userdata = false;
                        }
                    }
                    if (userdata) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,  MainActivity.class);
                        intent.putExtra("username", name);
                        intent.putExtra("password", password);  //展示账号密码功能
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
       mSQlite = new SQLite(LoginActivity.this);

}
}

