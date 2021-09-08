package com.example.intelligentnavigationcane.GHJ.The_First;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.intelligentnavigationcane.R;

import static android.content.Context.SENSOR_SERVICE;
import static com.baidu.mapapi.map.BaiduMap.MAP_TYPE_NONE;
import static com.baidu.mapapi.map.BaiduMap.MAP_TYPE_NORMAL;
import static com.baidu.mapapi.map.BaiduMap.MAP_TYPE_SATELLITE;

public class Map_Bottom extends Fragment implements SensorEventListener {
    private RadioGroup mGroup;
    private ImageButton ib_loc;
    private BaiduMap mBaiduMap;
    private MapView mMapView;
    //定位相关
    private LocationClient mLocationClient;
    //是否第一次定位，如果是第一次定位的话要将自己的位置显示在地图中间
    private boolean isFirstLocation = true;
    private Double lastX = 0.0;
    private float mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private MyLocationData myLocationData;
    private float mCurrentAccracy;
    private SensorManager mSensorManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map_bottom, container, false);
        mMapView = view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        ib_loc = view.findViewById(R.id.ib_loc);
        mGroup = view.findViewById(R.id.mGroup);
        //初始化相关按钮以及定位配置
        initView();
        initBtn();
        initLocation();
        //初始化方向传感器
        return view;

    }

    private void initView() {
        MyLocationConfiguration myLocationConfiguration =
                new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
        // 设置定位图层配置信息
        mBaiduMap.setMyLocationConfiguration(myLocationConfiguration);
        // 获取传感器管理服务
        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    private void initBtn() {
        ib_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstLocation = true;
                showInfo("返回自己位置");
            }
        });
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.map_btn_1:
                        mBaiduMap.setMapType(MAP_TYPE_NORMAL);
                        mBaiduMap.setTrafficEnabled(false);
                        break;
                    case R.id.map_btn_2:
                        mBaiduMap.setMapType(MAP_TYPE_SATELLITE);
                        mBaiduMap.setTrafficEnabled(false);
                        break;
                    case R.id.map_btn_3:
                        mBaiduMap.setTrafficEnabled(true);
                        break;
                    case R.id.map_btn_4:
                        mBaiduMap.setMapType(MAP_TYPE_NONE);
                        break;
                }
            }
        });
    }

    private void initLocation() {
        //定位客户端的设置
        mLocationClient = new LocationClient(getActivity());
        //注册监听
        mLocationClient.registerLocationListener(mListener);
        //配置定位
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy); //设置定位模式LocationMode.Hight_Accuracy：高精度
        option.setCoorType("bd09ll");//坐标类型
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//打开Gps
        option.setScanSpan(1000);//1000毫秒定位一次
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClient.setLocOption(option);
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        /**
         * 定位请求回调函数
         *
         * @param location 定位结果
         */
        @Override
        public void onReceiveLocation(BDLocation location) {
            // MapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            myLocationData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)// 设置定位数据的精度信息，单位：米
                    .direction(mCurrentDirection)// 此处设置开发者获取到的方向信息，顺时针0-360
                    .latitude(mCurrentLat)
                    .longitude(mCurrentLon)
                    .build();
            mBaiduMap.setMyLocationData(myLocationData);
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation
                    || location.getLocType() == BDLocation.TypeOffLineLocation) {
                if (isFirstLocation) {
                    isFirstLocation = false;
                    LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    showInfo("位置：" + location.getAddrStr());
                }
            }
        }
    };

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (float) x;
            // 构造定位图层数据
            myLocationData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection)
                    .latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            // 设置定位图层数据
            mBaiduMap.setMyLocationData(myLocationData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //显示消息
    private void showInfo(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }
}


