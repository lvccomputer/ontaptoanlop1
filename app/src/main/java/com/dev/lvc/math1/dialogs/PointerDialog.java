package com.dev.lvc.math1.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.PointerAdapter;
import com.dev.lvc.math1.models.Pointer;

import java.util.ArrayList;

public class PointerDialog extends Dialog {

    private RecyclerView recyclerView;
    private Context context;
    private PointerAdapter adapter;
    private TextView tvReview, tvNext;
    private String point;
    private TextView pointer;

    private PointerListener pointerListener;

    private ArrayList<Pointer> pointers;
    public PointerDialog(@NonNull Context context, ArrayList<Pointer> pointers,String point,PointerListener pointerListener) {
        super(context, R.style.Dialog_Theme);
        this.pointers = pointers;
        this.pointerListener = pointerListener;
        this.context = context;
        this.point = point;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pointer);
        setCancelable(false);
        recyclerView = findViewById(R.id.rcvPoint);
        tvReview =findViewById(R.id.tvReview);
        tvNext = findViewById(R.id.tvNext);
        pointer = findViewById(R.id.tvPointer);
        pointer.setText(point+"/10");
        tvReview.setOnClickListener(v -> {
            dismiss();
            if (pointerListener!=null)pointerListener.onReview();
        });
        tvNext.setOnClickListener(v -> {
            dismiss();
            if (pointerListener!=null)pointerListener.onNext();
        });

        adapter =new PointerAdapter(pointers,context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

    }

    public interface PointerListener{
        void onReview();
        void onNext();
    }
}
