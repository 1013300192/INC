package com.example.intelligentnavigationcane.GHJ.The_First;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.intelligentnavigationcane.R;

public class Test_1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_test1,container,false);

        CardView card_0 = view.findViewById(R.id.hello);
        card_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is Hello!", Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_1 = view.findViewById(R.id.card_1);
        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_1!",Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_2 = view.findViewById(R.id.card_2);
        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_2!",Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_3 = view.findViewById(R.id.card_3);
        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"This is card_3!",Toast.LENGTH_SHORT).show();
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
}