package com.dev.lvc.math1.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dev.lvc.math1.R;

public class SubmitTestsDialog extends Dialog {

    private TextView tvNo,tvSure;

    private SubmitTestListener submitTestListener;
    private Context context;

    public SubmitTestsDialog(@NonNull Context context,SubmitTestListener submitTestListener) {
        super(context,R.style.Dialog_Theme);
        this.submitTestListener = submitTestListener;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_submit_tests);
        setCancelable(false);
        tvNo = findViewById(R.id.tvNo);
        tvSure= findViewById(R.id.tvSure);
        tvNo.setOnClickListener(v -> {
            dismiss();
            if (submitTestListener!=null)submitTestListener.Cancel();
        });
        tvSure.setOnClickListener(v -> {
            dismiss();
            if (submitTestListener!=null)submitTestListener.Submit();
        });

    }

    public interface SubmitTestListener{
        void Cancel();
        void Submit();
    }
}
