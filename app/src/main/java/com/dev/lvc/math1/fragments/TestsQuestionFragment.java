package com.dev.lvc.math1.fragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dev.lvc.math1.EndTime;
import com.dev.lvc.math1.dialogs.PointerDialog;
import com.dev.lvc.math1.dialogs.SubmitTestsDialog;
import com.dev.lvc.math1.models.Pointer;
import com.dev.lvc.math1.utils.AnswerArrayCache;
import com.dev.lvc.math1.utils.DateTimeUtils;
import com.dev.lvc.math1.utils.JsonUtils;
import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.test.QuestionPagerAdapter;
import com.dev.lvc.math1.models.TestsQuestion;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestsQuestionFragment extends BaseFragment {

    private String timeStart;

    private String dateStart;

    private String exam;

    private int pointer = 0;

    private String[] Answers = new String[10];

    private String[] Result = new String[10];

    private ImageView imgBack;

    private TextView tvTime;

    private ImageView nextQuestion, previousQuestion;

    private ViewPager viewPager;

    private ImageView imgSubmit;

    private String id;

    private QuestionPagerAdapter questionPagerAdapter;
    private boolean checkSubmit = false;

    public void setId(String id) {
        this.id = id;
    }

    private CounterClass timerDown;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exam = "Bài" + id;
        timeStart = DateTimeUtils.time();
        dateStart = DateTimeUtils.date();
        getResult();
        init();

    }

    private void init() {

        viewPager = view.findViewById(R.id.viewPager);
        imgBack = view.findViewById(R.id.imgBack);
        tvTime = view.findViewById(R.id.tvTime);
        nextQuestion = view.findViewById(R.id.nextQuestion);
        previousQuestion = view.findViewById(R.id.previousQuestion);
        imgSubmit = view.findViewById(R.id.imgSubmit);

        questionPagerAdapter = new QuestionPagerAdapter(JsonUtils.loadTestsJsonData(mainActivity, id), mainActivity, false) {
            @Override
            public void update(String answer, int position) {
                Answers[position - 1] = answer;
            }
        };

        viewPager.setAdapter(questionPagerAdapter);
        int limit = (questionPagerAdapter.getCount() > 1 ? questionPagerAdapter.getCount() - 1 : 1);

        viewPager.setOffscreenPageLimit(limit);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == (viewPager.getAdapter().getCount() - 1)) {
                    if (!checkSubmit)
                        imgSubmit.setVisibility(View.VISIBLE);
                    nextQuestion.setVisibility(View.GONE);
                } else {
                    imgSubmit.setVisibility(View.GONE);
                    nextQuestion.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        nextQuestion.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(mainActivity, R.raw.click);
            mediaPlayer.start();
            animate(nextQuestion);
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        });
        previousQuestion.setOnClickListener(v -> {
            MediaPlayer mediaPlayer = MediaPlayer.create(mainActivity, R.raw.click);
            mediaPlayer.start();
            animate(previousQuestion);
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        });
        imgBack.setOnClickListener(v -> {
            Log.e("cuong", "click");

            animate(imgBack);
            mainActivity.onBackPressed();
        });

        timerDown = new CounterClass(10 * 60 * 1000, 1000);

        timerDown.start();

        imgSubmit.setOnClickListener(v -> {
            SubmitTestsDialog dialog = new SubmitTestsDialog(mainActivity, false, new SubmitTestsDialog.SubmitTestListener() {
                @Override
                public void Cancel() {
                }

                @Override
                public void Submit() {
                    timerDown.cancel();
                    ArrayList<Pointer> pointers = new ArrayList<>();
                    for (int index = 0; index < Result.length; index++) {
                        Pointer point = new Pointer();
                        point.setPosition(index);
                        if (Answers[index] != null) {
                            if (Answers[index].equals(Result[index])) {
                                pointer++;
                                point.setTick(R.drawable.ic_true);
                            } else point.setTick(R.drawable.ic_false);
                        } else point.setTick(R.drawable.ic_exclam);

                        pointers.add(point);
                    }
                    ContentValues values = new ContentValues();
                    values.put("title", exam);
                    values.put("time", tvTime.getText().toString());
                    values.put("pointer", pointer);
                    values.put("timestart", "Làm bài lúc " + timeStart + " ngày " + dateStart);
                    values.put("addtime",dateStart);
                    mainActivity.historySqlite.insert("History", null, values);
                    PointerDialog pointerDialog = new PointerDialog(mainActivity, pointers, String.valueOf(pointer), new PointerDialog.PointerListener() {
                        @Override
                        public void onReview() {
                            questionPagerAdapter = new QuestionPagerAdapter(JsonUtils.loadTestsJsonData(mainActivity, id), mainActivity, true) {
                                @Override
                                public void update(String answer, int position) {

                                }
                            };
                            viewPager.setAdapter(questionPagerAdapter);
                            checkSubmit = true;
                        }

                        @Override
                        public void onNext() {
                            mainActivity.getSupportFragmentManager().popBackStack();

                        }
                    });
                    pointerDialog.show();
                }
            });
            dialog.show();
        });


        timerDown.setEndTime(() -> {
            SubmitTestsDialog dialog = new SubmitTestsDialog(mainActivity, true, new SubmitTestsDialog.SubmitTestListener() {
                @Override
                public void Cancel() {

                }

                @Override
                public void Submit() {
                    timerDown.cancel();
                    ArrayList<Pointer> pointers = new ArrayList<>();
                    for (int index = 0; index < Result.length; index++) {
                        Pointer point = new Pointer();
                        point.setPosition(index);
                        if (Answers[index] != null) {
                            if (Answers[index].equals(Result[index])) {
                                pointer++;
                                point.setTick(R.drawable.ic_true);
                            } else point.setTick(R.drawable.ic_false);
                        } else point.setTick(R.drawable.ic_exclam);

                        pointers.add(point);
                    }
                    ContentValues values = new ContentValues();
                    values.put("title", exam);
                    values.put("time", tvTime.getText().toString());
                    values.put("pointer", pointer);
                    values.put("timestart", "Làm bài lúc " + timeStart + " ngày " + dateStart);
                    mainActivity.historySqlite.insert("History", null, values);
                    PointerDialog pointerDialog = new PointerDialog(mainActivity, pointers, String.valueOf(pointer), new PointerDialog.PointerListener() {
                        @Override
                        public void onReview() {
                            questionPagerAdapter = new QuestionPagerAdapter(JsonUtils.loadTestsJsonData(mainActivity, id), mainActivity, true) {
                                @Override
                                public void update(String answer, int position) {

                                }
                            };
                            viewPager.setAdapter(questionPagerAdapter);
                            checkSubmit = true;
                        }

                        @Override
                        public void onNext() {
                            mainActivity.getSupportFragmentManager().popBackStack();

                        }
                    });
                    pointerDialog.show();
                }
            });
            dialog.show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setUserInterface(view.findViewById(R.id.testsQuestion));
    }

    private void getResult() {
        ArrayList<TestsQuestion> testsQuestions = JsonUtils.loadTestsJsonData(mainActivity, id);

        for (int index = 0; index < testsQuestions.size(); index++) {
            Result[index] = testsQuestions.get(index).getResult();
        }
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

    public class CounterClass extends CountDownTimer {
        private EndTime endTime;

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            @SuppressLint("DefaultLocale") String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTime.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTime.setText("00:00");  //SetText cho textview hiện thị thời gian.
            if (endTime != null) endTime.endTime();
        }

        public void setEndTime(EndTime endTime) {
            this.endTime = endTime;
        }
    }

    @Override
    protected int getIdResource() {
        return R.layout.fragment_tests_question;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AnswerArrayCache.array = new String[10];
    }


}
