package com.dev.lvc.math1.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.AttributeSet;
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
import com.dev.lvc.math1.utils.SoundUtils;
import com.dev.lvc.math1.activities.MainActivity;
import com.dev.lvc.math1.models.TestsQuestion;

public class TestsQuestionView extends FrameLayout {


    private EditText edtAnswer;

    private TextView tvRequest, tvQuestion;

    private RelativeLayout writeAnswer;

    private RelativeLayout lnA, lnB, lnC, lnD;

    private TextView tvAnswerA, tvAnswerB, tvAnswerC, tvAnswerD;

    private Button btnCheckAnswer;

    private LinearLayout lnAnswer;

    private TextView tvBodyA, tvBodyB, tvBodyC, tvBodyD;

    private ImageView checkA, checkB, checkC, checkD;

    private TestsQuestion testsQuestion;

    private ImageView imgQuestion;

    private Callback callback;

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

    @SuppressLint("SetTextI18n")
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
        btnCheckAnswer.setText("Đồng ý");

        if (btnCheckAnswer.getText().toString().equals("Đồng ý")){
            btnCheckAnswer.setOnClickListener(v -> {
                btnCheckAnswer.setText("Sửa");
                MainActivity.hideKeyBoard((Activity) getContext());
                callback.updateAnswer(edtAnswer.getText().toString(),testsQuestion.getId());
            });
        }else {
            btnCheckAnswer.setOnClickListener(v -> {
                edtAnswer.requestFocus();
                InputMethodManager imm = (InputMethodManager) App.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(edtAnswer, InputMethodManager.SHOW_IMPLICIT);
                btnCheckAnswer.setText("Đồng ý");
            });

        }

        lnA.setOnClickListener(v -> {
            selectA();
        });
        lnB.setOnClickListener(v -> {
            selectB();

        });
        lnC.setOnClickListener(v -> {
            selectC();

        });
        lnD.setOnClickListener(v -> {
            selectD();
        });
    }

    private void animate(LinearLayout lnAnswer) {
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
        callback.updateAnswer(tvAnswerA.getText().toString(),testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_select);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectB() {
        callback.updateAnswer(tvAnswerB.getText().toString(),testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_select);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectC() {
        callback.updateAnswer(tvAnswerC.getText().toString(),testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_select);
        lnD.setBackgroundResource(R.drawable.bg_answer);
    }

    private void selectD() {
        callback.updateAnswer(tvAnswerD.getText().toString(),testsQuestion.getId());
        lnA.setBackgroundResource(R.drawable.bg_answer);
        lnB.setBackgroundResource(R.drawable.bg_answer);
        lnC.setBackgroundResource(R.drawable.bg_answer);
        lnD.setBackgroundResource(R.drawable.bg_select);
    }

    private void BCDFalse() {
        checkB.setVisibility(VISIBLE);
        checkC.setVisibility(VISIBLE);
        checkD.setVisibility(VISIBLE);
        checkA.setVisibility(VISIBLE);

        checkA.setImageResource(R.drawable.ic_true);
        checkB.setImageResource(R.drawable.ic_false);
        checkC.setImageResource(R.drawable.ic_false);
        checkD.setImageResource(R.drawable.ic_false);

        lnB.setBackgroundResource(R.drawable.bg_false);
        lnC.setBackgroundResource(R.drawable.bg_false);
        lnD.setBackgroundResource(R.drawable.bg_false);

        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ACDFlase() {
        checkA.setVisibility(VISIBLE);
        checkC.setVisibility(VISIBLE);
        checkD.setVisibility(VISIBLE);
        checkB.setVisibility(VISIBLE);

        checkB.setImageResource(R.drawable.ic_true);
        checkA.setImageResource(R.drawable.ic_false);
        checkC.setImageResource(R.drawable.ic_false);
        checkD.setImageResource(R.drawable.ic_false);

        lnA.setBackgroundResource(R.drawable.bg_false);
        lnC.setBackgroundResource(R.drawable.bg_false);
        lnD.setBackgroundResource(R.drawable.bg_false);

        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ABDFalse() {
        checkA.setVisibility(VISIBLE);
        checkB.setVisibility(VISIBLE);
        checkD.setVisibility(VISIBLE);
        checkC.setVisibility(VISIBLE);

        checkC.setImageResource(R.drawable.ic_true);
        checkA.setImageResource(R.drawable.ic_false);
        checkB.setImageResource(R.drawable.ic_false);
        checkD.setImageResource(R.drawable.ic_false);

        lnA.setBackgroundResource(R.drawable.bg_false);
        lnB.setBackgroundResource(R.drawable.bg_false);
        lnD.setBackgroundResource(R.drawable.bg_false);
        lnA.setEnabled(false);
        lnB.setEnabled(false);
        lnC.setEnabled(false);
        lnD.setEnabled(false);
    }

    private void ABCFalse() {
        checkA.setVisibility(VISIBLE);
        checkB.setVisibility(VISIBLE);
        checkC.setVisibility(VISIBLE);
        checkD.setVisibility(VISIBLE);

        checkD.setImageResource(R.drawable.ic_true);
        checkA.setImageResource(R.drawable.ic_false);
        checkB.setImageResource(R.drawable.ic_false);
        checkC.setImageResource(R.drawable.ic_false);

        lnA.setBackgroundResource(R.drawable.bg_false);
        lnB.setBackgroundResource(R.drawable.bg_false);
        lnC.setBackgroundResource(R.drawable.bg_false);

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

    public interface Callback{
        void updateAnswer(String answer, int position);
    }

}
