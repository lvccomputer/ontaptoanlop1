package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.HistoryAdapter;
import com.dev.lvc.math1.models.History;

import java.util.ArrayList;

public class HistoryFragment extends BaseFragment {

    private ArrayList<History> historyArrayList;

    private HistoryAdapter adapter;

    private RecyclerView rcvHistory;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initID();
        initView();
    }

    private void initView() {

        historyArrayList.add(new History("Đề 1","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 2","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 3","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 4","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 5","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 6","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));
        historyArrayList.add(new History("Đề 7","Làm bài lúc 20:42:50 ngày 10/11/2019","Điểm 9/10 Thời gian làm 14:30s"));

        adapter = new HistoryAdapter(historyArrayList,mainActivity);
        rcvHistory.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvHistory.setAdapter(adapter);
    }

    private void initID(){
        historyArrayList = new ArrayList<>();
        rcvHistory = view.findViewById(R.id.rcvHistory);

    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_history;
    }
}
