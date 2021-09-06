package com.example.intelligentnavigationcane.GHJ.Test_Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.intelligentnavigationcane.R;
import com.example.intelligentnavigationcane.connect;

import java.sql.SQLException;


public class RegisterActivity extends AppCompatActivity {
    public static int conn_on= 0;//用于判断连接是否成功
    public static int re_call=2;
    public static int call=0;
    public static String password_receive;//用于接收数据库查询的返回数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username =  findViewById(R.id.edit_Rname);//取得输入框的对象
        final EditText password =  findViewById(R.id.edit_Rpass);


        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(conn_on==1){
                    if(re_call==1){
                        if(call==1){
                            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,Main_LoginActivity.class);
                            startActivity(intent);}
                        else if(call==0){
                            Toast.makeText(RegisterActivity.this,"信息未填写完整",Toast.LENGTH_SHORT).show();
                        }
                    }else if(re_call==0) {
                        Toast.makeText(RegisterActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
                    }
                }else if(conn_on==0){
                    Toast.makeText(RegisterActivity.this,"网络未连接",Toast.LENGTH_SHORT).show();
                }



                return true;
            }
        });
        Button Register = findViewById(R.id.btn_ready);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        try {
                            connect.insertIntoData(username.getText().toString(), password.getText().toString());//判断用户名是否存在再插入数据
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        handler.sendMessage(msg);
                        }
                }).start();

            }
        });
    }
    }


