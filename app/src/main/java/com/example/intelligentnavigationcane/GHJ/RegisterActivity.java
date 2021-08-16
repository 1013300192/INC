package com.example.intelligentnavigationcane.GHJ;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.intelligentnavigationcane.R;


public class RegisterActivity extends AppCompatActivity {
    private SQLite mSQlite;
    private EditText username;
    private EditText userpassword;
    private Button reday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reday=findViewById(R.id.btn_ready);
        username=findViewById(R.id.edit_Rname);
        userpassword=findViewById(R.id.edit_Rpass);
        reday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String password = userpassword.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password)){
                    mSQlite.add(name,password);
                    Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(RegisterActivity.this,"信息不完备，注册失败",Toast.LENGTH_SHORT).show();}

            }
        });
        mSQlite = new SQLite(RegisterActivity.this);
    }
}
