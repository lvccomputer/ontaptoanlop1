package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.Utils;
import com.dev.lvc.math1.adapters.ListOfPracticeAdapter;
import com.dev.lvc.math1.models.Practice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListOfPracticeFragment extends BaseFragment {

    private static final String TAG ="cuong";

    private RecyclerView rcvListOfPractice;

    private ListOfPracticeAdapter listOfPracticeAdapter;

    private ArrayList<Practice> practiceArrayList;

    private TextView tvTitle;

    private String titleToolBar;

    private String id;

    private String folder;
    private String nameImage;

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitleToolBar(String titleToolBar) {
        this.titleToolBar = titleToolBar;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
        loadDataFromJson();
    }

    private void initID(){
        tvTitle = view.findViewById(R.id.tvTitle);
        practiceArrayList = new ArrayList<>();
        rcvListOfPractice = view.findViewById(R.id.rcvLuyenTap);
    }

    private void initView(){
        tvTitle.setText(titleToolBar);
        listOfPracticeAdapter = new ListOfPracticeAdapter(practiceArrayList,mainActivity);
        rcvListOfPractice.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvListOfPractice.setAdapter(listOfPracticeAdapter);
        listOfPracticeAdapter.setOnClickItem((position, data) -> {

        });

    }
    private void loadDataFromJson(){
        try {
            JSONObject practiceObject = new JSONObject(Utils.loadJSONFromAssets(mainActivity, "chapter.json"));
            JSONArray practiceJsonArray = practiceObject.getJSONArray("chapter_"+ id);
            for (int index = 0; index < practiceJsonArray.length(); index++) {
                JSONObject prac_inside = practiceJsonArray.getJSONObject(index);
                Practice practice = new Practice();
                practice.setIcon(nameImage);
                practice.setFolderImage(folder);
                practice.setIdPractice(Integer.valueOf(prac_inside.getString("id")));
                practice.setTitlePractice(prac_inside.getString("title"));
                practiceArrayList.add(practice);
            }
        } catch (JSONException e) {
            Log.e(TAG, "ERROR JSON: "+e );
        }
        listOfPracticeAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getIdResource() {
        return R.layout.fragment_try_hard;
    }
}
