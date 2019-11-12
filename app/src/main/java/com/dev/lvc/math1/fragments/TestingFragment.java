package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.lvc.math1.R;

public class TestingFragment extends BaseFragment{

    private RelativeLayout layoutA,layoutB,layoutC,layoutD;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
    }

    private void initID(){
//        layoutA = view.findViewById(R.id.layoutA);
//        layoutC = view.findViewById(R.id.layoutC);
//        layoutD = view.findViewById(R.id.layoutD);
//        layoutB = view.findViewById(R.id.layoutB);
//
//        layoutA.setBackgroundResource(R.drawable.bg_false);
//        layoutB.setBackgroundResource(R.drawable.bg_false);
//        layoutC.setBackgroundResource(R.drawable.bg_false);

    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_testing1;
    }
}
