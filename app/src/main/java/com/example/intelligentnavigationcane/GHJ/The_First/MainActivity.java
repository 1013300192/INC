package com.example.intelligentnavigationcane.GHJ.The_First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.baidu.mapapi.SDKInitializer;

import com.example.intelligentnavigationcane.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int checked = 1;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_thefirst_);

        fragmentManager = getSupportFragmentManager();
        RadioButton btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(this);
        RadioButton btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(this);
        RadioButton btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(this);
        RadioButton btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(this);

        if (savedInstanceState != null) { // “内存重启”时调用

            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);

            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0+""));
            fragments.add(fragmentManager.findFragmentByTag(1+""));
            fragments.add(fragmentManager.findFragmentByTag(2+""));
            fragments.add(fragmentManager.findFragmentByTag(3+""));

            //恢复fragment页面
            restoreFragment();

        }else{      //正常启动时调用
            fragments.add(new ShouYe_Activity());
            fragments.add(new Map_Bottom());
            fragments.add(new User());
            fragments.add(new User());
            showFragment();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT,currentIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_1:
                if (checked!=1){
                    currentIndex=0;
                    showFragment();}
                checked=1;
                break;
            case R.id.btn_2:
                if (checked!=2){
                    currentIndex=1;
                    showFragment();}
                checked=2;
                break;
            case R.id.btn_3:
                if (checked!=3){
                    currentIndex=2;
                    showFragment();}
                checked=3;
                break;
            case R.id.btn_4:
                if (checked!=4){
                    currentIndex=3;
                    showFragment();}
                checked=4;
                break;
            default:
                break;
        }
    }

    private void showFragment(){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(!fragments.get(currentIndex).isAdded()){
            transaction
                    .hide(currentFragment)
                    .add(R.id.top_frame_layout,fragments.get(currentIndex),""+currentIndex);
        }else{
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(currentIndex));
        }

        //全局变量，记录当前显示的fragment
        currentFragment = fragments.get(currentIndex);
        transaction.commit();
    }

    //恢复fragment
    private void restoreFragment(){

        FragmentTransaction begin = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {

            if(i == currentIndex){
                begin.show(fragments.get(i));
            }else{
                begin.hide(fragments.get(i));
            }
        }

        begin.commit();
        currentFragment = fragments.get(currentIndex);
    }

}
