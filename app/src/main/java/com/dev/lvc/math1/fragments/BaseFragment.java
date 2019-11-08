package com.dev.lvc.math1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dev.lvc.math1.activities.MainActivity;

public abstract class BaseFragment extends Fragment {

    protected View view;
    protected MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getIdResource(), container, false);

        mainActivity = (MainActivity) getActivity();
        return view;
    }

    protected abstract int getIdResource();
}
