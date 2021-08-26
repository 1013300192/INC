package com.example.intelligentnavigationcane.GHJ.The_First;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.mapapi.map.MapView;
import com.example.intelligentnavigationcane.R;
import com.example.intelligentnavigationcane.YBF.Map;


public class MainActivity extends AppCompatActivity {
    private Button mBtn1,mBtn2,mBtn3,mBtn4;
    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thefirst_);
        mBtn1=findViewById(R.id.btn_1);
        mBtn2=findViewById(R.id.btn_2);
        mBtn3=findViewById(R.id.btn_3);
        mBtn4=findViewById(R.id.btn_4);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.top_frame_layout,new ShouYe_Activity());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.top_frame_layout,new Map());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    /**@Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }*/
}
