package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.Utils;
import com.dev.lvc.math1.adapters.practice.QuestionPagerAdapter;

public class PracticeQuestionFragment extends BaseFragment {

    private ImageView imgBack;

    private TextView tvThreadName;

    private ImageView nextQuestion, previousQuestion;
    private ViewPager pagerQuestion;

    private String id;

    private String practiceId;

    public void setId(String id) {
        this.id = id;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {
        pagerQuestion = view.findViewById(R.id.pagerQuestion);
        imgBack = view.findViewById(R.id.imgBack);
        tvThreadName = view.findViewById(R.id.tvThreadName);
        nextQuestion = view.findViewById(R.id.nextQuestion);
        previousQuestion = view.findViewById(R.id.previousQuestion);

        pagerQuestion.setAdapter(new QuestionPagerAdapter(Utils.loadPracticeJsonData(mainActivity, id, practiceId), mainActivity));
        nextQuestion.setOnClickListener(v -> {
            pagerQuestion.setCurrentItem(pagerQuestion.getCurrentItem()+1);
        });
        previousQuestion.setOnClickListener(v -> {
            pagerQuestion.setCurrentItem(pagerQuestion.getCurrentItem()-1);

        });
        tvThreadName.setText("Bài " + practiceId);
    }


    @Override
    protected int getIdResource() {
        return R.layout.fragment_practice_question;
    }
}
