package com.example.zaevtour.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String userName;
    String titleText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = getContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        userName = sharedPreferences.getString("userName", "데이터 없음");

        // 텍스트 Gradient 적용
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        TextView titleTextView = v.findViewById(R.id.titleText);

        titleText = userName + "님" + "\n여행을 떠나볼까요?";
        titleTextView.setText(titleText);

        Tvg.change(titleTextView, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        MainActivity activity = (MainActivity) getActivity();
        ImageView profile = v.findViewById(R.id.profileImage);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(1);
            }
        });

        return v;
    }
}