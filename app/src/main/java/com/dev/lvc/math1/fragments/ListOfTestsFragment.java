package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.test.TestAdapter;
import com.dev.lvc.math1.models.Tests;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListOfTestsFragment extends BaseFragment {

    private FloatingActionButton floatBack;

    private RecyclerView rcvKiemTra;

    private TestAdapter kiemTraTestAdapter;

    private ArrayList<Tests> testsArrayList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID(){
        testsArrayList = new ArrayList<>();
        rcvKiemTra = view.findViewById(R.id.rcvExams);
        floatBack = view.findViewById(R.id.floatBack);

    }
    private void initView(){
        floatBack.setOnClickListener(v -> mainActivity.onBackPressed());

        testsArrayList.add(new Tests(R.drawable.ic_test_list,"Bài 1"));
        testsArrayList.add(new Tests(R.drawable.ic_test_list,"Bài 2"));
        testsArrayList.add(new Tests(R.drawable.ic_test_list,"Bài 3"));
        testsArrayList.add(new Tests(R.drawable.ic_test_list,"Bài 4"));
        testsArrayList.add(new Tests(R.drawable.ic_test_list,"Bài 5"));

        kiemTraTestAdapter = new TestAdapter(testsArrayList,mainActivity);
        rcvKiemTra.setLayoutManager(new GridLayoutManager(mainActivity,4));
        rcvKiemTra.setAdapter(kiemTraTestAdapter);
        kiemTraTestAdapter.setOnClickItemKiemTra(position ->{
            mainActivity.showBaiLamFragment(String.valueOf(position+1));
        });

    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_list_of_tests;
    }
}
