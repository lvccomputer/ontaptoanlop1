package com.dev.lvc.math1.adapters.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemHistoryAdapter> {

    private ArrayList<History> histories;

    private Context context;

    public HistoryAdapter(ArrayList<History> histories, Context context) {
        this.histories = histories;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHistoryAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_history, parent, false);

        return new ItemHistoryAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHistoryAdapter holder, int position) {

        History  history= histories.get(position);
        holder.tvResult.setText(history.getResult());
        holder.tvLesson.setText(history.getLesson());
        holder.tvHistory.setText(history.getTime());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ItemHistoryAdapter extends RecyclerView.ViewHolder {
        private TextView tvLesson, tvHistory, tvResult;

        public ItemHistoryAdapter(@NonNull View itemView) {
            super(itemView);
            tvHistory = itemView.findViewById(R.id.tvHistory);
            tvLesson = itemView.findViewById(R.id.tvLesson);
            tvResult = itemView.findViewById(R.id.tvResult);
        }
    }
}
