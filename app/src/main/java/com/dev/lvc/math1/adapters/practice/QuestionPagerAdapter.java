package com.dev.lvc.math1.adapters.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.ItemQuestionPractice;
import com.dev.lvc.math1.views.QuestionPracticeView;

import java.util.ArrayList;

public class QuestionPagerAdapter extends PagerAdapter {

    private ArrayList<ItemQuestionPractice> itemQuestionPractices;

    private Context context;

    public QuestionPagerAdapter(ArrayList<ItemQuestionPractice> itemQuestionPractices, Context context) {
        this.itemQuestionPractices = itemQuestionPractices;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemQuestionPractices.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        QuestionPracticeView view = (QuestionPracticeView) inflater.inflate(R.layout.item_pager_question,container,false);
        view.setItemQuestionPractice(itemQuestionPractices.get(position));
        view.initData();
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
