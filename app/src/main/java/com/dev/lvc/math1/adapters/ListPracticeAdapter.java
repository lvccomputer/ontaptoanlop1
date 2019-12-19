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

public class ListPracticeAdapter extends RecyclerView.Adapter<ListPracticeAdapter.ItemListPraViewHolder> {
    private ArrayList<Data> arrayList;
    private Context context;
    private OnClickItem onClickItem;

    public ListPracticeAdapter(ArrayList<Data> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemListPraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_training, parent, false);
        return new ItemListPraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListPraViewHolder holder, int position) {

        Data data = arrayList.get(position);
        holder.imgLogo.setImageResource(data.getIcon());
        holder.tvType.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemListPraViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo;
        private TextView tvType;

        public ItemListPraViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgTraining);
            tvType = itemView.findViewById(R.id.tvTitleTraining);
            itemView.setOnClickListener(v -> {
                if (onClickItem != null)
                    onClickItem.setOnClickItem(getAdapterPosition(), arrayList.get(getAdapterPosition()));
            });
            itemView.getLayoutParams().height = (int) (context.getResources().getDisplayMetrics().heightPixels / 3);
            itemView.getLayoutParams().width = (int) (context.getResources().getDisplayMetrics().widthPixels / 2);

        }
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void setOnClickItem(int position, Data data);
    }
}
