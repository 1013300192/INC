package com.example.intelligentnavigationcane.GHJ.Test_Login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.intelligentnavigationcane.R;
import com.example.intelligentnavigationcane.connect;

import java.sql.SQLException;

public class ForgetActivity extends AppCompatActivity {
    public static int conn_on=0;
    public static int call=0;
    public static int re_call=0;
    public static String find_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        final EditText phone_number=findViewById(R.id.edit_phone);
        final EditText name=findViewById(R.id.edit_person_name);
        final TextView find=findViewById(R.id.tv_find);

        final Handler handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                if(conn_on==1){
                    if(call==1){
                        if(re_call==1){
                            find.setText("您的密码是："+find_password);
                        }else if(re_call==0){
                            Toast.makeText(ForgetActivity.this,"信息不正确",Toast.LENGTH_SHORT).show();
                        }
                    }else if(call==0){
                        Toast.makeText(ForgetActivity.this,"电话或姓名为空",Toast.LENGTH_SHORT).show();
                    }
                }else if(conn_on==0){
                    Toast.makeText(ForgetActivity.this,"网络未连接",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        Button phone_register=findViewById(R.id.btn_phone);
        phone_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg=new Message();
                        try {
                            find_password=connect.Phone(phone_number.getText().toString(),name.getText().toString());
                        }catch (SQLException e){
                            e.printStackTrace();
                        }handler.sendMessage(msg);

                    }
                }).start();
            }
        });
    }
}
