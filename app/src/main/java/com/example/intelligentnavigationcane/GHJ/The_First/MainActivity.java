package com.example.intelligentnavigationcane.GHJ.The_First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.intelligentnavigationcane.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int checked = 0;
    private Fragment currentFragment = new Fragment();
    private BaiduMap mBaiduMap;
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    //是否第一次定位，如果是第一次定位的话要将自己的位置显示在地图 中间
    private boolean isFirstLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_thefirst_);
        MapView mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        //定位
        initLocation();

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
    private void initLocation() {
        //定位客户端的设置
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        //注册监听
        mLocationClient.registerLocationListener(mLocationListener);
        //配置定位
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");//坐标类型
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//打开Gps
        option.setScanSpan(1000);//1000毫秒定位一次
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

    //自定义的定位监听
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //将获取的location信息给百度map
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);
            if (isFirstLocation) {
                //获取经纬度
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
                //mBaiduMap.setMapStatus(status);//直接到中间
                mBaiduMap.animateMapStatus(status);//动画的方式到中间
                isFirstLocation = false;
                showInfo("位置：" + location.getAddrStr());
            }
        }
    }
    //显示消息
    private void showInfo(String str){
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if(!mLocationClient.isStarted()){
            mLocationClient.start();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        //关闭定位
        mBaiduMap.setMyLocationEnabled(false);
        if(mLocationClient.isStarted()){
            mLocationClient.stop();
        }
    }

    /**@Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView = findViewById(R.id.bmapView);
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
