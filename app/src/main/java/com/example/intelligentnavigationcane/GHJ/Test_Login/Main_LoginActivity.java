package com.example.intelligentnavigationcane.GHJ.Test_Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intelligentnavigationcane.GHJ.The_First.MainActivity;
import com.example.intelligentnavigationcane.R;
import com.example.intelligentnavigationcane.connect;

import java.sql.SQLException;

public class Main_LoginActivity extends CheckPermissionsActivity {
    public static int conn_on=0;//用于判断连接是否成功
    public static int Recall=0;
    //public static int R_call=0;
   // public static String L_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username =  findViewById(R.id.edit_name);//取得输入框的对象
        final EditText password =  findViewById(R.id.edit_pass);



        Button Register = findViewById(R.id.btn_register);
        Register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        Button forget=findViewById(R.id.btn_forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main_LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });

        final Handler handler2 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(conn_on==1){
                    if(Recall==1){
                        Toast.makeText(Main_LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Main_LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else if(Recall==0){
                        Toast.makeText(Main_LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(conn_on==0){
                    Toast.makeText(Main_LoginActivity.this,"未连接网络",Toast.LENGTH_SHORT).show();
                }
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
                           connect.Recall_Data(username.getText().toString(),password.getText().toString());//调用查询语句，获得账号对应的密码
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }handler2.sendMessage(msg);//跳转到handler2//

                    }
                }).start();

            }
        });

    }
}


