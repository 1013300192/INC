package com.example.intelligentnavigationcane.GHJ.The_First;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.intelligentnavigationcane.LXY.Test;
import com.example.intelligentnavigationcane.LXY.Tools.SP;
import com.example.intelligentnavigationcane.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class User extends Fragment {

    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;

    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user,container,false);

        CardView card_0 = view.findViewById(R.id.hello);
        card_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"安全出行，为您护航!", Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_1 = view.findViewById(R.id.card_1);
        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getActivity());
                bottomView = getLayoutInflater().inflate(R.layout.dialog_center, null);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);

                EditText editText = bottomView.findViewById(R.id.edit);
                TextView textView = bottomView.findViewById(R.id.convince);

                TextView name = view.findViewById(R.id.name);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editText.getText().toString().equals("")){
                            Toast.makeText(getActivity(),
                                    "error",Toast.LENGTH_SHORT).show();}
                        else{
                        name.setText(editText.getText().toString());}
                        bottomSheetDialog.cancel();
                    }
                });
                bottomSheetDialog.show();
            }
        });
        CardView card_2 = view.findViewById(R.id.card_2);
        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Test.class);
                startActivity(intent);
            }
        });
        CardView card_3 = view.findViewById(R.id.card_3);
        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        CardView card_4 = view.findViewById(R.id.card_4);
        card_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_4!",Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_5 = view.findViewById(R.id.card_5);
        card_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_5!",Toast.LENGTH_SHORT).show();
            }
        });CardView card_6 = view.findViewById(R.id.card_6);
        card_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_6!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();

        ImageView imageView = getView().findViewById(R.id.image);
        String imageUrl = SP.getString("imageUrl",null,getActivity());
        if(imageUrl != null){
            Glide.with(getActivity()).load(imageUrl).apply(requestOptions).into(imageView);
        }
    }
}