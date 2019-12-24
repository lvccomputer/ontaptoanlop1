package com.dev.lvc.math1.adapters.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.TestsQuestion;
import com.dev.lvc.math1.views.TestsQuestionView;

import java.util.ArrayList;

public abstract class QuestionPagerAdapter extends PagerAdapter {

    private ArrayList<TestsQuestion> testsQuestionArrayList;

    private Context context;

    private boolean check;

    public QuestionPagerAdapter(ArrayList<TestsQuestion> testsQuestionArrayList, Context context, boolean check) {
        this.testsQuestionArrayList = testsQuestionArrayList;
        this.context = context;
        this.check = check;
    }

    @Override
    public int getCount() {
        return testsQuestionArrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        TestsQuestionView view = (TestsQuestionView) inflater.inflate(R.layout.item_pager_tests_question, container, false);
        view.setTestsQuestion(testsQuestionArrayList.get(position));
        view.setCallback((answer, position1) -> {
            update(answer,position1);
        });
        view.initData();
        view.setReview(check);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    public abstract void update(String answer,int position);



}

