package com.dev.lvc.math1.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dev.lvc.math1.App;
import com.dev.lvc.math1.R;
import com.dev.lvc.math1.utils.AnswerArrayCache;
import com.dev.lvc.math1.utils.SoundUtils;
import com.dev.lvc.math1.activities.MainActivity;
import com.dev.lvc.math1.models.TestsQuestion;

public class TestsQuestionView extends FrameLayout {

    private EditText edtAnswer;

    private TextView tvRequest, tvQuestion;

    private RelativeLayout writeAnswer,lnWrite;

    private RelativeLayout lnA, lnB, lnC, lnD;

    private TextView tvAnswerA, tvAnswerB, tvAnswerC, tvAnswerD;

    private Button btnCheckAnswer;

    private LinearLayout lnAnswer;

    private TextView tvBodyA, tvBodyB, tvBodyC, tvBodyD;

    private ImageView checkA, checkB, checkC, checkD;

    private TestsQuestion testsQuestion;

    private ImageView imgQuestion;

    private Callback callback;

    private boolean isReview = false;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public TestsQuestionView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_tests_view, null);
        addView(view);
        initView();
    }

    public void setTestsQuestion(TestsQuestion testsQuestion) {
        this.testsQuestion = testsQuestion;
    }

    private void initView() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvRequest = findViewById(R.id.tvRequest);
        edtAnswer = findViewById(R.id.edtWriteAnswer);
        writeAnswer = findViewById(R.id.writeAnswer);
        lnWrite = findViewById(R.id.lnWrite);

        lnA = findViewById(R.id.lnA);
        lnB = findViewById(R.id.lnB);
        lnC = findViewById(R.id.lnC);
        lnD = findViewById(R.id.lnD);

        tvAnswerA = findViewById(R.id.tvAnswerA);
        tvAnswerB = findViewById(R.id.tvAnswerB);
        tvAnswerC = findViewById(R.id.tvAnswerC);
        tvAnswerD = findViewById(R.id.tvAnswerD);

        tvBodyA = findViewById(R.id.tvBodyA);
        tvBodyB = findViewById(R.id.tvBodyB);
        tvBodyC = findViewById(R.id.tvBodyC);
        tvBodyD = findViewById(R.id.tvBodyD);

        checkA = findViewById(R.id.imgCheckA);
        checkB = findViewById(R.id.imgCheckB);
        checkC = findViewById(R.id.imgCheckC);
        checkD = findViewById(R.id.imgCheckD);

        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        lnAnswer = findViewById(R.id.lnAnswer);
        imgQuestion = findViewById(R.id.imgQuestion);

    }

    public void setReview(boolean review) {
        if (review) {
            reView();
        }

    }

    public void initData() {
        tvRequest.setText("Câu " + testsQuestion.getId() + ": " + testsQuestion.getRequest());
        Glide.with(getContext())
                .load(testsQuestion.getRequestImage())
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        tvQuestion.setText(testsQuestion.getRequestText());
                        tvQuestion.setVisibility(VISIBLE);
                        imgQuestion.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        tvQuestion.setVisibility(GONE);
                        imgQuestion.setVisibility(VISIBLE);
                        return false;
                    }
                }).into(imgQuestion);
        tvBodyA.setText(testsQuestion.getA());
        tvBodyB.setText(testsQuestion.getB());
        tvBodyC.setText(testsQuestion.getC());
        tvBodyD.setText(testsQuestion.getD());
        if (testsQuestion.getA().equals("") && testsQuestion.getB().equals("") && testsQuestion.getC().equals("")
                && testsQuestion.getD().equals("")) {
            lnAnswer.setVisibility(GONE);
            writeAnswer.setVisibility(VISIBLE);
            edtAnswer.setVisibility(VISIBLE);
        }

        if (testsQuestion.getC().equals("") && testsQuestion.getD().equals("")) {
            lnC.setVisibility(GONE);
            lnD.setVisibility(GONE);
        }
        edtAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                callback.updateAnswer(s.toString(), testsQuestion.getId());
                AnswerArrayCache.array[testsQuestion.getId()-1]=s.toString();

            }
        });

        btnCheckAnswer.setOnClickListener(v -> {
            if (btnCheckAnswer.getText().toString().equals("Đồng ý")) {
                MainActivity.hideKeyBoard((Activity) getContext());
            } else {
                edtAnswer.requestFocus();
                InputMethodManager imm = (InputMethodManager) App.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtAnswer, InputMethodManager.SHOW_IMPLICIT);
                btnCheckAnswer.setText("Đồng ý");
            }
        });

        lnA.setOnClickListener(v -> {
            animate(lnA);
            AnswerArrayCache.array[testsQuestion.getId() - 1] = tvAnswerA.getText().toString();
            selectA();
        });
        lnB.setOnClickListener(v -> {
            animate(lnB);
            AnswerArrayCache.array[testsQuestion.getId() - 1] = tvAnswerB.getText().toString();
            selectB();

        });
        lnC.setOnClickListener(v -> {
            animate(lnC);
            AnswerArrayCache.array[testsQuestion.getId() - 1] = tvAnswerC.getText().toString();
            selectC();

        });
        lnD.setOnClickListener(v -> {
            animate(lnD);
            AnswerArrayCache.array[testsQuestion.getId() - 1] = tvAnswerD.getText().toString();
            selectD();
        });
    }

    private void reView() {
        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
        edtAnswer.setEnabled(false);
        btnCheckAnswer.setVisibility(GONE);

        String AnswerUser = AnswerArrayCache.array[testsQuestion.getId()-1];
        if (AnswerUser!=null){
            if (testsQuestion.getA().equals("") && testsQuestion.getB().equals("") && testsQuestion.getC().equals("")
                    && testsQuestion.getD().equals("")) {
                if (AnswerUser.equals(testsQuestion.getResult())){
                    edtAnswer.setText(AnswerArrayCache.array[testsQuestion.getId()-1]);
                    lnWrite.setBackgroundResource(R.drawable.bg_true);
                }else {
                    edtAnswer.setText(AnswerArrayCache.array[testsQuestion.getId()-1]);
                    lnWrite.setBackgroundResource(R.drawable.bg_not);
                }
            } else {
                if (AnswerUser.equals(tvAnswerA.getText().toString())) {
                    lnA.setBackgroundResource(R.drawable.bg_select);
                    lnB.setBackgroundResource(R.drawable.bg_answer);
                    lnC.setBackgroundResource(R.drawable.bg_answer);
                    lnD.setBackgroundResource(R.drawable.bg_answer);

                } else if (AnswerUser.equals(tvAnswerB.getText().toString())) {
                    lnA.setBackgroundResource(R.drawable.bg_answer);
                    lnB.setBackgroundResource(R.drawable.bg_select);
                    lnC.setBackgroundResource(R.drawable.bg_answer);
                    lnD.setBackgroundResource(R.drawable.bg_answer);

                } else if (AnswerUser.equals(tvAnswerC.getText().toString())) {
                    lnA.setBackgroundResource(R.drawable.bg_answer);
                    lnB.setBackgroundResource(R.drawable.bg_answer);
                    lnC.setBackgroundResource(R.drawable.bg_select);
                    lnD.setBackgroundResource(R.drawable.bg_answer);

                } else if (AnswerUser.equals(tvAnswerD.getText().toString())) {
                    lnA.setBackgroundResource(R.drawable.bg_answer);
                    lnB.setBackgroundResource(R.drawable.bg_answer);
                    lnC.setBackgroundResource(R.drawable.bg_answer);
                    lnD.setBackgroundResource(R.drawable.bg_select);
                }
            }
        }


        if (tvAnswerA.getText().toString().equals(testsQuestion.getResult())) {
            BCDFalse();
        } else if (tvAnswerB.getText().toString().equals(testsQuestion.getResult())) {
            ACDFlase();
        } else if (tvAnswerC.getText().toString().equals(testsQuestion.getResult())) {
            ABDFalse();
        } else if (tvAnswerD.getText().toString().equals(testsQuestion.getResult())) {
            ABCFalse();
        }
    }

    private void animate(RelativeLayout lnAnswer) {
        lnAnswer.animate()
                .scaleY(0.9f)
                .scaleX(0.9f)
                .setDuration(50)
                .withEndAction(() -> lnAnswer.animate()
                        .scaleY(1)
                        .scaleX(1)
                        .setDuration(50)
                        .start())
                .start();
    }

    private void selectA() {
        callback.updateAnswer(tvAnswerA.getText().toString(), testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_select);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectB() {
        callback.updateAnswer(tvAnswerB.getText().toString(), testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_select);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectC() {
        callback.updateAnswer(tvAnswerC.getText().toString(), testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_select);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectD() {
        callback.updateAnswer(tvAnswerD.getText().toString(), testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_select);
    }

    private void BCDFalse() {
        checkA.setVisibility(VISIBLE);

        checkA.setImageResource(R.drawable.ic_true);
        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ACDFlase() {
        checkB.setVisibility(VISIBLE);

        checkB.setImageResource(R.drawable.ic_true);


        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ABDFalse() {
        checkC.setVisibility(VISIBLE);

        checkC.setImageResource(R.drawable.ic_true);

        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ABCFalse() {
        checkD.setVisibility(VISIBLE);

        checkD.setImageResource(R.drawable.ic_true);
        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void checkWriteAnswer(String result) {
        if (result.equals(testsQuestion.getResult())) {
            Tick();
        } else Error();
    }


    private void Error() {
        int soundFalse = SoundUtils.getRandomSoundFalse();
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), soundFalse);
        mediaPlayer.start();
    }


    private void Tick() {
        int soundTrue = SoundUtils.getRandomSoundTrue();
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), soundTrue);
        mediaPlayer.start();
        Vibrator vibrator = (Vibrator) App.getApp().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(250);
        }
    }

    public interface Callback {
        void updateAnswer(String answer, int position);
    }

}
