package com.example.intelligentnavigationcane.GHJ.Test_Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    public static int conn_on = 0;//用于判断连接是否成功
    public static String password_receive;//用于接收数据库查询的返回数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username =  findViewById(R.id.edit_Rname);//取得输入框的对象
        final EditText password =  findViewById(R.id.edit_Rpass);

        final TextView connR =  findViewById(R.id.conn_R);//取得网络提示框的对象、
        connR.setBackgroundColor(Color.RED);//默认设成红色
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (conn_on)//根据返回值判断网络连接是否成功
                {
                    case 1:
                        connR.setText("网络连接成功");
                        connR.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        connR.setText("网络连接失败");
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    connect.getConnection("inc");//执行连接测试
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);//跳转到handler1
            }
        }).start();
        Button Register = findViewById(R.id.btn_ready);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            connect.insertIntoData(username.getText().toString(),password.getText().toString());//调用插入数据库语句
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(RegisterActivity.this,Main_LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

