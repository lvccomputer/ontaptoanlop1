package com.dev.lvc.math1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.Data;

import java.util.ArrayList;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ItemLuyenTapViewHolder> {


    private ArrayList<Data> arrayList;

    private Context context;

    public PracticeAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemLuyenTapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

        return new ItemLuyenTapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLuyenTapViewHolder holder, int position) {
        Data data = arrayList.get(position);

        holder.imgLuyenTap.setImageResource(data.getIcon());
        holder.tvLuyenTap.setText(data.getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemLuyenTapViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLuyenTap;

        private ImageView imgLuyenTap;

        public ItemLuyenTapViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLuyenTap = itemView.findViewById(R.id.tvLuyenTap);
            imgLuyenTap = itemView.findViewById(R.id.imgLuyenTap);

            tvLuyenTap.setHorizontallyScrolling( true );
            tvLuyenTap.setSelected( true );
            tvLuyenTap.requestLayout();
        }
    }
}
