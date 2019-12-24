package com.dev.lvc.math1.adapters.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.PracticeQuestion;
import com.dev.lvc.math1.views.PracticeQuestionView;

import java.util.ArrayList;

public class QuestionPagerAdapter extends PagerAdapter {

    private ArrayList<PracticeQuestion> practiceQuestions;

    private Context context;

    public QuestionPagerAdapter(ArrayList<PracticeQuestion> practiceQuestions, Context context) {
        this.practiceQuestions = practiceQuestions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return practiceQuestions.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        PracticeQuestionView view = (PracticeQuestionView) inflater.inflate(R.layout.item_pager_question,container,false);
        view.setItemQuestionPractice(practiceQuestions.get(position));
        view.initData();
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
