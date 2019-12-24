package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.history.HistoryAdapter;
import com.dev.lvc.math1.models.History;
import com.dev.lvc.math1.utils.SqliteUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HistoryFragment extends BaseFragment {

    private ImageView imgBack;

    private HistoryAdapter adapter;

    private RecyclerView rcvHistory;

    private TextView tvStatus;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initID();
        initView();
    }

    private void initView() {
        if (SqliteUtils.getHistories(mainActivity).size() == 0) {
            tvStatus.setText("Chưa có bài nào được làm! Mời các bé làm bài kiểm tra!");
        } else tvStatus.setText("Bài làm sẽ được xóa trong 10 ngày tính từ ngày bắt đầu!");
        adapter = new HistoryAdapter(SqliteUtils.getHistories(mainActivity), mainActivity);
        rcvHistory.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvHistory.setAdapter(adapter);
    }

    private void initID() {
        rcvHistory = view.findViewById(R.id.rcvHistory);
        tvStatus = view.findViewById(R.id.status);
        imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> mainActivity.onBackPressed());

    }

    @Override
    protected int getIdResource() {
        return R.layout.fragment_history;
    }
}
