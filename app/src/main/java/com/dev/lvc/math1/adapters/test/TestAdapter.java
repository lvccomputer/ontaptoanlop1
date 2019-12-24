package com.dev.lvc.math1.adapters.test;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.OnClickItemKiemTra;
import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.Tests;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ItemKiemTraViewHolder> {

    private ArrayList<Tests> arrayList;

    private Context context;

    private OnClickItemKiemTra onClickItemKiemTra;

    public TestAdapter(ArrayList<Tests> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemKiemTraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_tests,parent,false);

        return new ItemKiemTraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemKiemTraViewHolder holder, int position) {
        Tests tests = arrayList.get(position);

        holder.imgIconOfTests.setImageResource(tests.getIcon());
        holder.tvNameOfTests.setText(tests.getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemKiemTraViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameOfTests;
        private ImageView imgIconOfTests;

        private View view;
        public ItemKiemTraViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameOfTests = itemView.findViewById(R.id.tvType);
            imgIconOfTests = itemView.findViewById(R.id.imgLogo);
            tvNameOfTests.setGravity(Gravity.CENTER_HORIZONTAL );
            itemView.getLayoutParams().height = (int) (context.getResources().getDisplayMetrics().heightPixels / 6);
            itemView.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels / 4);
            itemView.setOnClickListener(v -> {
                if (onClickItemKiemTra!=null) onClickItemKiemTra.setOnClickItemKiemTra(getAdapterPosition());

            });
        }
    }

    public void setOnClickItemKiemTra(OnClickItemKiemTra onClickItemKiemTra) {
        this.onClickItemKiemTra = onClickItemKiemTra;
    }
}
