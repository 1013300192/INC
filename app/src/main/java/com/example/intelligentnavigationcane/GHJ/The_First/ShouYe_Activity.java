package com.example.intelligentnavigationcane.GHJ.The_First;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.intelligentnavigationcane.R;

import java.util.zip.Inflater;

public class ShouYe_Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_shou_ye,container,false);
        return view;
    }
}
