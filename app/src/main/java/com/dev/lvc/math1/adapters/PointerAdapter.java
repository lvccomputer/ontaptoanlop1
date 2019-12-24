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
import com.dev.lvc.math1.models.Pointer;

import java.util.ArrayList;

public class PointerAdapter extends RecyclerView.Adapter<PointerAdapter.ItemPointerViewHolder> {
    private ArrayList<Pointer> pointers;
    private Context context;

    public PointerAdapter(ArrayList<Pointer> pointers, Context context) {
        this.pointers = pointers;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemPointerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_point,parent,false);
        return new ItemPointerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPointerViewHolder holder, int position) {

        Pointer pointer = pointers.get(position);
        holder.tvPosition.setText((pointer.getPosition()+1)+"");
        holder.imgTick.setImageResource(pointer.getTick());
    }

    @Override
    public int getItemCount() {
        return pointers.size();
    }

    public class ItemPointerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPosition;
        private ImageView imgTick;

        public ItemPointerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            imgTick = itemView.findViewById(R.id.imgTick);

            itemView.getLayoutParams().height = (int) (context.getResources().getDisplayMetrics().heightPixels / 1);
            itemView.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels / 12);
        }
    }
}
