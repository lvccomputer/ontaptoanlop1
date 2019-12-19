package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.math1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestFragment extends BaseFragment{

    private RelativeLayout btnDifficult, btnEasy;

    private FloatingActionButton btnBack;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID(){
        btnBack = view.findViewById(R.id.floatBack);
        btnDifficult = view.findViewById(R.id.btnHard);
        btnEasy = view.findViewById(R.id.btnEasy);

    }
    private void initView(){
        btnEasy.setOnClickListener(v -> {
            mainActivity.showListOfEasyTests();

        });
        btnDifficult.setOnClickListener(v -> {
            mainActivity.showListOfDifficultTests();
        });
    }

    @Override
    protected int getIdResource() {
        return R.layout.fragment_test;
    }
}
