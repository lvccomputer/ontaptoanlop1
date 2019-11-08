package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.lvc.math1.R;
import com.dev.lvc.math1.adapters.KiemTraAdapter;
import com.dev.lvc.math1.models.Data;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class KiemTraFragment extends BaseFragment {

    private FloatingActionButton floatBack;

    private RecyclerView rcvKiemTra;

    private KiemTraAdapter kiemTraAdapter;

    private ArrayList<Data> dataArrayList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initID();
        initView();
    }

    private void initID(){
        dataArrayList = new ArrayList<>();
        rcvKiemTra = view.findViewById(R.id.rcvKiemTra);
        floatBack = view.findViewById(R.id.floatBack);

    }
    private void initView(){
        floatBack.setOnClickListener(v -> mainActivity.onBackPressed());

        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 1. Nhiều Hơn, Ít Hơn"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 2. Hình Vuông, Hình Tròn, Hình Tam Giác"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 3. Lớn Hơn, Bé Hơn, Bằng,Dấu < > ="));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 4. Phép Cộng Trong Phạm Vi 3"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 5. Phép Cộng Trong Phạm Vi 5"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 6. Phép Trừ Trong Phạm Vi 3"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 7. Phép Trừ Trong Phạm Vi 5"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 8. Điểm, đoạn thẳng"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 9. Mười một, mười hai"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 10. Các số tròn chục"));
        dataArrayList.add(new Data(R.drawable.ic_high,"Bài 11. Giải bài toán có lời văn"));

        kiemTraAdapter = new KiemTraAdapter(dataArrayList,mainActivity);
        rcvKiemTra.setLayoutManager(new LinearLayoutManager(mainActivity));
        rcvKiemTra.setAdapter(kiemTraAdapter);


    }
    @Override
    protected int getIdResource() {
        return R.layout.fragment_kiem_tra;
    }
}
