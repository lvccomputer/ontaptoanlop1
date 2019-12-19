package com.dev.lvc.math1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.Utils;
import com.dev.lvc.math1.models.Practice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfPracticeAdapter extends RecyclerView.Adapter<ListOfPracticeAdapter.ItemViewHolder> {

    private ArrayList<Practice> practiceArrayList;

    private Context context;

    private OnClickItem onClickItem;
    public ListOfPracticeAdapter(ArrayList<Practice> practiceArrayList, Context context) {
        this.practiceArrayList = practiceArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_of_practice,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Practice practice = practiceArrayList.get(position);
        Picasso.get().load(Utils.URI + practice.getFolderImage() + "/" + practice.getIcon()).into(holder.imgIcon);
        Log.e("cuong", "onBindViewHolder: "+Utils.URI + practice.getFolderImage() + "/" + practice.getIcon() );
        holder.tvTitle.setText(practice.getTitlePractice());
    }

    @Override
    public int getItemCount() {
        return practiceArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitlePractice);
            itemView.setOnClickListener(v -> {
                if (onClickItem!=null)onClickItem.setOnClickItem(getAdapterPosition(),practiceArrayList.get(getAdapterPosition()));
            });

        }
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem{
        void setOnClickItem(int position, Practice practice);
    }
}
