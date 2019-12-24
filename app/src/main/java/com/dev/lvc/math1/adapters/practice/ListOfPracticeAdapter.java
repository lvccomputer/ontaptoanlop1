package com.dev.lvc.math1.adapters.practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.utils.JsonUtils;
import com.dev.lvc.math1.models.Practice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListOfPracticeAdapter extends RecyclerView.Adapter<ListOfPracticeAdapter.ItemViewHolder> {

    private ArrayList<Practice> practiceArrayList;

    private Context context;

    private OnClickPrac onClickPrac;

    public ListOfPracticeAdapter(ArrayList<Practice> practiceArrayList, Context context) {
        this.practiceArrayList = practiceArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_of_practice, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Practice practice = practiceArrayList.get(position);
        Picasso.get().load(JsonUtils.URI + practice.getFolderImage() + "/" + practice.getIcon()).into(holder.imgIcon);
        holder.tvTitle.setText(practice.getTitlePractice());
        holder.view.setOnClickListener(v -> {
            if (onClickPrac != null)
                onClickPrac.setOnClickItem(position, practiceArrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return practiceArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvTitle;

        private View view;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitlePractice);
            view = itemView.findViewById(R.id.clickView);
        }
    }

    public void setOnClickPrac(OnClickPrac onClickPrac) {
        this.onClickPrac = onClickPrac;
    }

    public interface OnClickPrac {
        void setOnClickItem(int position, Practice practice);
    }
}
