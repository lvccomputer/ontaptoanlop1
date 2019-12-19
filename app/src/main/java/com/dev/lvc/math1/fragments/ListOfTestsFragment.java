package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.TestAdapter;
import com.dev.lvc.math1.models.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListOfTestsFragment extends BaseFragment {

    private FloatingActionButton floatBack;

    private RecyclerView rcvKiemTra;

    private TestAdapter kiemTraTestAdapter;

    private ArrayList<Data> dataArrayList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID(){
        dataArrayList = new ArrayList<>();
        rcvKiemTra = view.findViewById(R.id.rcvExams);
        floatBack = view.findViewById(R.id.floatBack);

    }
    private void initView(){
        floatBack.setOnClickListener(v -> mainActivity.onBackPressed());

        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 1"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 2"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 3"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 4"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 5"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 6"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 7"));
        dataArrayList.add(new Data(R.drawable.ic_test_list,"Bài 8"));

        kiemTraTestAdapter = new TestAdapter(dataArrayList,mainActivity);
        rcvKiemTra.setLayoutManager(new GridLayoutManager(mainActivity,4));
        rcvKiemTra.setAdapter(kiemTraTestAdapter);
        kiemTraTestAdapter.setOnClickItemKiemTra(position ->{
            mainActivity.showBaiLamFragment();
        });

    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_list_of_tests;
    }
}
