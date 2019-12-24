package com.dev.lvc.math1.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.utils.JsonUtils;
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
        initView();
    }

    private void init() {
        pagerQuestion = view.findViewById(R.id.pagerQuestion);
        imgBack = view.findViewById(R.id.imgBack);
        tvThreadName = view.findViewById(R.id.tvThreadName);
        nextQuestion = view.findViewById(R.id.nextQuestion);
        previousQuestion = view.findViewById(R.id.previousQuestion);


    }

    private void initView(){
        QuestionPagerAdapter adapter= new QuestionPagerAdapter(JsonUtils.loadPracticeJsonData(mainActivity, id, practiceId), mainActivity);
        pagerQuestion.setAdapter(adapter);
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);

        pagerQuestion.setOffscreenPageLimit(limit);

        nextQuestion.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(mainActivity,R.raw.click);
            mediaPlayer.start();
            animate(nextQuestion);
            pagerQuestion.setCurrentItem(pagerQuestion.getCurrentItem()+1);
        });
        previousQuestion.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(mainActivity,R.raw.click);
            mediaPlayer.start();
            animate(previousQuestion);
            pagerQuestion.setCurrentItem(pagerQuestion.getCurrentItem()-1);

        });
        tvThreadName.setText("Bài " + practiceId);

        imgBack.setOnClickListener(v -> {
            Log.e("cuong", "click" );
            mainActivity.onBackPressed();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setUserInterface(view.findViewById(R.id.practiceQuestion));
    }
    private void animate(ImageView imageView) {
        imageView.animate()
                .scaleY(0.9f)
                .scaleX(0.9f)
                .setDuration(50)
                .withEndAction(() -> imageView.animate()
                        .scaleY(1)
                        .scaleX(1)
                        .setDuration(50)
                        .start())
                .start();
    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_practice_question;
    }
}
