package com.dev.lvc.math1.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dev.lvc.math1.R;

public class SubmitTestsDialog extends Dialog {

    private TextView tvNo, tvSure,tvStatus;

    private SubmitTestListener submitTestListener;

    private boolean isEnd;

    private Context context;

    public SubmitTestsDialog(@NonNull Context context, boolean isEnd, SubmitTestListener submitTestListener) {
        super(context, R.style.Dialog_Theme);
        this.submitTestListener = submitTestListener;
        this.context = context;
        this.isEnd = isEnd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_submit_tests);
        setCancelable(false);
        tvNo = findViewById(R.id.tvNo);
        tvSure = findViewById(R.id.tvSure);
        tvStatus = findViewById(R.id.tvStatus);
        if (isEnd) {
            tvNo.setVisibility(View.GONE);
            tvSure.setText("Nộp bài");
            tvStatus.setText("Đã hết giờ làm bài!");
        } else {
            tvNo.setText("Không");
            tvSure.setText("Chắc chắn");

        }

        tvNo.setOnClickListener(v -> {
            dismiss();
            if (submitTestListener != null) submitTestListener.Cancel();
        });
        tvSure.setOnClickListener(v -> {
            dismiss();
            if (submitTestListener != null) submitTestListener.Submit();
        });
    }

    public interface SubmitTestListener {
        void Cancel();

        void Submit();
    }
}
