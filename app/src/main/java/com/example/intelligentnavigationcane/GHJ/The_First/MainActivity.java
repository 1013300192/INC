package com.example.intelligentnavigationcane.GHJ.The_First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.baidu.mapapi.map.MapView;

import com.example.intelligentnavigationcane.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int checked = 0;
    private Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thefirst_);

        MapView mMapView = findViewById(R.id.bmapView);

        RadioButton btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        RadioButton btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        RadioButton btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        RadioButton btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_1:
                if (checked!=1)
                    replaceFragment(new ShouYe_Activity());
                checked=1;
                break;
            case R.id.btn_2:
                if (checked!=2)
                    replaceFragment(new Map());
                checked=2;
                break;
            case R.id.btn_3:
                if (checked!=3)
                    replaceFragment(new Test_1());
                checked=3;
                break;
            case R.id.btn_4:
                if (checked!=4)
                    replaceFragment(new Test_1());
                checked=4;
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if(!fragment.isAdded()){
            transaction
                    .hide(currentFragment)
                    .replace(R.id.top_frame_layout,fragment);
        }else{
            transaction
                    .hide(currentFragment)
                    .show(fragment);
        }

        //全局变量，记录当前显示的fragment
        currentFragment = fragment;
        transaction.addToBackStack(null);
        transaction.commit();

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
