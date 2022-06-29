package com.example.zaevtour.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        // 텍스트 Gradient 적용
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = v.findViewById(R.id.titleText);
        Tvg.change(textView, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}