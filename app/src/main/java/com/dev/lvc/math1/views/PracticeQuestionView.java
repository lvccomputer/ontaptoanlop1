package com.dev.lvc.math1.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
import com.dev.lvc.math1.models.PracticeQuestion;


public class PracticeQuestionView extends FrameLayout {

    private EditText edtAnswer;

    private TextView tvRequest, tvQuestion;

    private RelativeLayout writeAnswer,lnWrite;

    private RelativeLayout lnA, lnB, lnC, lnD;
    private TextView tvAnswerA, tvAnswerB, tvAnswerC, tvAnswerD;
    private Button btnCheckAnswer;
    private LinearLayout lnAnswer;
    private TextView tvBodyA, tvBodyB, tvBodyC, tvBodyD;
    private ImageView checkA, checkB, checkC, checkD;
    private PracticeQuestion practiceQuestion;

    private ImageView imgQuestion;


    public PracticeQuestionView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_practice_view, null);
        addView(view);
        initView();
    }

    public void setItemQuestionPractice(PracticeQuestion practiceQuestion) {
        this.practiceQuestion = practiceQuestion;
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
        lnWrite = findViewById(R.id.lnWrite);


    }

    public void initData() {
        tvRequest.setText("Câu " + practiceQuestion.getId() + ": " + practiceQuestion.getRequest());
        Glide.with(getContext())
                .load(practiceQuestion.getRequestImage())
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        tvQuestion.setText(practiceQuestion.getRequestText());
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
        tvBodyA.setText(practiceQuestion.getA());
        tvBodyB.setText(practiceQuestion.getB());
        tvBodyC.setText(practiceQuestion.getC());
        tvBodyD.setText(practiceQuestion.getD());
        if (practiceQuestion.getA().equals("") && practiceQuestion.getB().equals("") && practiceQuestion.getC().equals("")
                && practiceQuestion.getD().equals("")) {
            lnAnswer.setVisibility(GONE);
            writeAnswer.setVisibility(VISIBLE);
            edtAnswer.setVisibility(VISIBLE);
        }

        if (practiceQuestion.getC().equals("") && practiceQuestion.getD().equals("")) {
            lnC.setVisibility(GONE);
            lnD.setVisibility(GONE);
        }

        btnCheckAnswer.setOnClickListener(v -> {
            MainActivity.hideKeyBoard((Activity) getContext());
            checkWriteAnswer(edtAnswer.getText().toString());
        });

        lnA.setOnClickListener(v -> {
            animate(lnA);
            if (tvAnswerA.getText().toString().equals(practiceQuestion.getResult())) {
                BCDFalse();
                Tick();
            } else {
                aFalse();
                Error();
            }

        });
        lnB.setOnClickListener(v -> {
            animate(lnB);

            if (tvAnswerB.getText().toString().equals(practiceQuestion.getResult())) {
                ACDFalse();
                Tick();
            } else {
                bFalse();
                Error();
            }
        });
        lnC.setOnClickListener(v -> {
            animate(lnC);

            if (tvAnswerC.getText().toString().equals(practiceQuestion.getResult())) {
                ABDFalse();
                Tick();
            } else {
                cFalse();
                Error();
            }
        });
        lnD.setOnClickListener(v -> {
            animate(lnD);

            if (tvAnswerD.getText().toString().equals(practiceQuestion.getResult())) {
                ABCFalse();
                Tick();
            } else {
                dFalse();
                Error();
            }
        });
    }


    private void aFalse() {
        checkA.setVisibility(VISIBLE);
        checkA.setImageResource(R.drawable.ic_false);
        lnA.setBackgroundResource(R.drawable.bg_false);
        lnA.setEnabled(false);
    }

    private void bFalse() {
        checkB.setVisibility(VISIBLE);
        checkB.setImageResource(R.drawable.ic_false);
        lnB.setBackgroundResource(R.drawable.bg_false);
        lnB.setEnabled(false);
    }

    private void cFalse() {
        checkC.setVisibility(VISIBLE);
        checkC.setImageResource(R.drawable.ic_false);
        lnC.setBackgroundResource(R.drawable.bg_false);
        lnC.setEnabled(false);
    }

    private void dFalse() {
        checkD.setVisibility(VISIBLE);
        checkD.setImageResource(R.drawable.ic_false);
        lnD.setBackgroundResource(R.drawable.bg_false);
        lnD.setEnabled(false);
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

    private void ACDFalse() {
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
        if (result.equals(practiceQuestion.getResult())) {
            edtAnswer.setEnabled(false);
            lnWrite.setBackgroundResource(R.drawable.bg_false);
            Tick();
        } else Error();
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

    private void Error() {
        int soundFalse = SoundUtils.getRandomSoundFalse();
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),soundFalse);
        mediaPlayer.start();
    }


    private void Tick() {
        int soundTrue = SoundUtils.getRandomSoundTrue();
        MediaPlayer mediaPlayer= MediaPlayer.create(getContext(),soundTrue);
        mediaPlayer.start();
        Vibrator vibrator = (Vibrator) App.getApp().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(250);
        }
    }

}
