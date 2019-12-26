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

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ItemTrainingAdapter> {

    private ArrayList<Practice> practiceArrayList;

    private Context context;

    private OnClickItemPractice onClickItemPractice;

    public PracticeAdapter(ArrayList<Practice> practiceArrayList, Context context) {
        this.practiceArrayList = practiceArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemTrainingAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_list_practices, parent, false);

        return new ItemTrainingAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTrainingAdapter holder, int position) {
        Practice practice = practiceArrayList.get(position);
        Picasso.get().load(JsonUtils.URI + practice.getFolderImage() + "/" + practice.getIcon()).into(holder.imgIcon);
        holder.tvTitleTrain.setText(practice.getTitlePractice());
    }

    @Override
    public int getItemCount() {
        return practiceArrayList.size();
    }

    public class ItemTrainingAdapter extends RecyclerView.ViewHolder {
        private ImageView imgIcon;
        private TextView tvTitleTrain;

        private View view;
        public ItemTrainingAdapter(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgTraining);
            tvTitleTrain = itemView.findViewById(R.id.tvTitleTraining);
            view = itemView.findViewById(R.id.itemView);

//            itemView.getLayoutParams().height = (int) (context.getResources().getDisplayMetrics().heightPixels / 3);
//            itemView.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels / 2);
            itemView.setOnClickListener(v -> {
                if (onClickItemPractice!=null)onClickItemPractice.setOnClickItemPractice(getAdapterPosition(),practiceArrayList.get(getAdapterPosition()));
            });

        }
    }

    public void setOnClickItemPractice(OnClickItemPractice onClickItemPractice) {
        this.onClickItemPractice = onClickItemPractice;
    }

    public interface OnClickItemPractice{
        void setOnClickItemPractice(int position, Practice practice);
    }
}
