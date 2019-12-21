package com.dev.lvc.math1.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dev.lvc.math1.App;
import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.ItemQuestionPractice;


public class QuestionPracticeView extends FrameLayout {

    private EditText edtAnswer;

    private TextView tvRequest, tvQuestion;

    private RelativeLayout writeAnswer;

    private RelativeLayout lnA, lnB, lnC, lnD;
    private TextView tvAnswerA, tvAnswerB, tvAnswerC, tvAnswerD;
    private Button btnCheckAnswer;
    private LinearLayout lnAnswer;
    private TextView tvBodyA, tvBodyB, tvBodyC, tvBodyD;
    private ImageView checkA, checkB, checkC, checkD;
    private ItemQuestionPractice itemQuestionPractice;

    private ImageView imgQuestion;


    public QuestionPracticeView(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_practice_view, null);
        addView(view);
        initView();
    }

    public void setItemQuestionPractice(ItemQuestionPractice itemQuestionPractice) {
        this.itemQuestionPractice = itemQuestionPractice;
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

    public void initData() {
        tvRequest.setText("Câu " + itemQuestionPractice.getId() + ": " + itemQuestionPractice.getRequest());
        Glide.with(getContext())
                .load(itemQuestionPractice.getRequestImage())
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        tvQuestion.setText(itemQuestionPractice.getRequestText());
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
//        rcvAnswer.setLayoutManager(new LinearLayoutManager(getContext()));
//        rcvAnswer.setNestedScrollingEnabled(false);
        tvBodyA.setText(itemQuestionPractice.getA());
        tvBodyB.setText(itemQuestionPractice.getB());
        tvBodyC.setText(itemQuestionPractice.getC());
        tvBodyD.setText(itemQuestionPractice.getD());
        if (itemQuestionPractice.getA().equals("") && itemQuestionPractice.getB().equals("") && itemQuestionPractice.getC().equals("")
                && itemQuestionPractice.getD().equals("")) {
            lnAnswer.setVisibility(GONE);
            writeAnswer.setVisibility(VISIBLE);
            edtAnswer.setVisibility(VISIBLE);
        }

        if (itemQuestionPractice.getC().equals("") && itemQuestionPractice.getD().equals("")){
            lnC.setVisibility(GONE);
            lnD.setVisibility(GONE);
        }

        btnCheckAnswer.setOnClickListener(v -> {
            checkWriteAnswer(edtAnswer.getText().toString());
        });

//        else {
////            AnswerAdapter answerAdapter = new AnswerAdapter(itemQuestionPractice.getAnswer());
//            rcvAnswer.setAdapter(answerAdapter);
//        }

    }

    private void checkWriteAnswer(String result) {
        if (result.equals(itemQuestionPractice.getResult())) {
            Tick();
        } else Error();
    }

    //    class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ItemAnswerViewHolder>{
//
//        private ArrayList<Answer> answers;
//
//        public AnswerAdapter(ArrayList<Answer> answers) {
//            this.answers = answers;
//        }
//
//        @NonNull
//        @Override
//        public ItemAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_answer_practice,parent,false);
//
//            return new ItemAnswerViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ItemAnswerViewHolder holder, int position) {
//            Answer answer = answers.get(position);
//            holder.tvAnswer.setText(answer.answer[position]);
//            holder.tvBody.setText(answer.getAnswers().get(position));
//
//            check(holder,answer,false);
//            holder.itemView.setOnClickListener(v -> {
//                click(holder,position);
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return answers.size();
//        }
//
//        public class ItemAnswerViewHolder extends RecyclerView.ViewHolder {
//            private TextView tvAnswer,tvBody;
//            private ImageView imgCheck;
//            private RelativeLayout lnAnswer;
//            public ItemAnswerViewHolder(@NonNull View itemView) {
//                super(itemView);
//                tvAnswer = itemView.findViewById(R.id.tvAnswer);
//                imgCheck = itemView.findViewById(R.id.imgCheck);
//                tvBody = itemView.findViewById(R.id.tvBody);
//                lnAnswer = itemView.findViewById(R.id.lnAnswer);
//                imgCheck.setVisibility(GONE);
//
//            }
//        }
//        private void click(ItemAnswerViewHolder holder,int position){
//            for (int i = 0;i<answers.size();i++){
//                if(position!=i){
//                    if(!answers.get(i).isResult()||!answers.get(position).isResult()){
//                        answers.get(i).setSelect(false);
//                        notifyItemChanged(i,false);
//                    }
//                }
//            }
//            answers.get(position).setSelect(!answers.get(position).isSelect());
//            check(holder,answers.get(position),true);
//
//        }
//        private void check(ItemAnswerViewHolder holder, Answer answer, boolean isClick){
//            if(answer.isSelect()){
//                if(answer.isResult()){
//                    holder.tvAnswer.setTextColor(Color.GREEN);
//                    holder.tvBody.setTextColor(Color.GREEN);
//                    holder.imgCheck.setVisibility(VISIBLE);
//                    holder.imgCheck.setImageResource(R.drawable.ic_true);
//                    if(isClick)Tick();
//                }else {
//                    holder.lnAnswer.setBackgroundResource(R.drawable.bg_false);
//                    holder.imgCheck.setVisibility(VISIBLE);
//                    holder.imgCheck.setImageResource(R.drawable.ic_false);
//                    if(isClick)Error();
//                }
//            }else {
//                holder.imgCheck.setVisibility(GONE);
//                holder.tvBody.setTextColor(Color.parseColor("#FF5722"));
//                holder.tvAnswer.setTextColor(Color.parseColor("#FF5722"));
//            }
//
//
//        }
//    }
    private void Error() {
        Toast.makeText(getContext(), "Bé làm sai rồi!", Toast.LENGTH_SHORT).show();
    }


    private void Tick() {
        Toast.makeText(getContext(), "Bé làm đúng rồi", Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) App.getApp().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            vibrator.vibrate(250);
        }
    }

}
