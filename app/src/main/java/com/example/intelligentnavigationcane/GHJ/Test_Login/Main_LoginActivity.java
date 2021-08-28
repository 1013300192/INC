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

import com.example.intelligentnavigationcane.GHJ.The_First.MainActivity;
import com.example.intelligentnavigationcane.R;
import com.example.intelligentnavigationcane.connect;

import java.sql.SQLException;

public class Main_LoginActivity extends AppCompatActivity {
    public static int conn_on=0;//用于判断连接是否成功
    public static String password_receive;//用于接收数据库查询的返回数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username =  findViewById(R.id.edit_name);//取得输入框的对象
        final EditText password =  findViewById(R.id.edit_pass);

        final TextView conn =  findViewById(R.id.conn);//取得网络提示框的对象
        conn.setBackgroundColor(Color.RED);//默认设成红色
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (conn_on)//根据返回值判断网络连接是否成功
                {
                    case 1:conn.setText("网络连接成功");conn.setBackgroundColor(Color.GREEN);break;
                    case 2:conn.setText("网络连接失败");break;
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

        Button Register = findViewById(R.id.btn_register);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        final Handler handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(password_receive.equals(password.getText().toString())){//判断输入密码与取得的密码是否相同
                    Toast.makeText(Main_LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Main_LoginActivity.this, MainActivity.class);
                    startActivity(intent);}
                else
                    Toast.makeText(Main_LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button logon = findViewById(R.id.btn_login);
        logon.setOnClickListener(new View.OnClickListener() {//登录
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        try {
                            password_receive= connect.querycol(username.getText().toString());//调用查询语句，获得账号对应的密码
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }handler2.sendMessage(msg);//跳转到handler2、
                    }
                }).start();

            }
        });

    }
}


