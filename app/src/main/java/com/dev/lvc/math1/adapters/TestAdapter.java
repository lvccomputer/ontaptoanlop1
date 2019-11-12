package com.dev.lvc.math1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.OnClickItemKiemTra;
import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.Data;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ItemKiemTraViewHolder> {

    private ArrayList<Data> arrayList;

    private Context context;

    private OnClickItemKiemTra onClickItemKiemTra;

    public TestAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemKiemTraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

        return new ItemKiemTraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemKiemTraViewHolder holder, int position) {
        Data data = arrayList.get(position);

        holder.imgKiemtra.setImageResource(data.getIcon());
        holder.tvKiemtra.setText(data.getName());
        holder.view.setOnClickListener(v -> {
            if (onClickItemKiemTra!=null) onClickItemKiemTra.setOnClickItemKiemTra(position);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemKiemTraViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKiemtra;
        private ImageView imgKiemtra;

        private View view;
        public ItemKiemTraViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKiemtra = itemView.findViewById(R.id.tvLuyenTap);
            imgKiemtra = itemView.findViewById(R.id.imgLuyenTap);
            tvKiemtra.setHorizontallyScrolling( true );
            tvKiemtra.setSelected( true );
            tvKiemtra.requestLayout();
            view = itemView.findViewById(R.id.clickItemLuyenTap);

        }
    }

    public void setOnClickItemKiemTra(OnClickItemKiemTra onClickItemKiemTra) {
        this.onClickItemKiemTra = onClickItemKiemTra;
    }
}
