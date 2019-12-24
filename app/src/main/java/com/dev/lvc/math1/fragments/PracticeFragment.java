package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.utils.JsonUtils;
import com.dev.lvc.math1.adapters.practice.PracticeAdapter;
import com.dev.lvc.math1.models.Practice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PracticeFragment extends BaseFragment {

    private static final String TAG = "cuong";

    private FloatingActionButton floatBack;

    private RecyclerView rcvLuyenTap;

    private PracticeAdapter practiceAdapter;

    private ArrayList<Practice> practiceArrayList;

    private TextView tvTitle;

    private String title;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initView();
    }

    private void init() {
        practiceArrayList = new ArrayList<>();
        rcvLuyenTap = view.findViewById(R.id.rcvLuyenTap);
        floatBack = view.findViewById(R.id.floatBack);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(title);


    }

    private void initView() {
        floatBack.setOnClickListener(v -> mainActivity.onBackPressed());
        practiceAdapter = new PracticeAdapter(practiceArrayList, mainActivity);
        rcvLuyenTap.setLayoutManager(new GridLayoutManager(mainActivity,2));
        rcvLuyenTap.setAdapter(practiceAdapter);
        practiceAdapter.setOnClickItemPractice((position, practice) -> {
            Log.e(TAG, "initView: " );
            mainActivity.showListOfPracticeFragment(String.valueOf(practice.getIdPractice()),practice.getTitlePractice(),practice.getFolderImage(),practice.getIcon());
        });

    }


    private void loadPracticeJsonData() {
        practiceArrayList.clear();
        try {
            JSONObject practiceObject = new JSONObject(JsonUtils.loadJSONFromAssets(mainActivity, "practice.json"));
            String folderImage = practiceObject.getString("image");
            Log.e(TAG, "loadPracticeJsonData: "+folderImage );
            JSONArray practiceJsonArray = practiceObject.getJSONArray("list");
            for (int index = 0; index < practiceJsonArray.length(); index++) {
                JSONObject prac_inside = practiceJsonArray.getJSONObject(index);
                Practice practice = new Practice();
                practice.setFolderImage(folderImage);
                practice.setIcon(prac_inside.getString("icon"));
                practice.setIdPractice(Integer.valueOf(prac_inside.getString("id")));
                practice.setTitlePractice(prac_inside.getString("title"));
                practiceArrayList.add(practice);
                Log.e(TAG, "loadPracticeJsonData: "+folderImage );
            }
        } catch (JSONException e) {
            Log.e(TAG, "ERROR JSON: "+e );
        }
        practiceAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadPracticeJsonData();
    }

    @Override
    protected int getIdResource() {
        return R.layout.fragment_list_of_practice;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
