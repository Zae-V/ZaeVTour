package com.example.zaevtour.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;

public class ModifyProfileView extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String userName;
    String userEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.modify_profile_view, container, false);

        sharedPreferences = getContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        userName = sharedPreferences.getString("userName", "데이터 없음");
        userEmail = sharedPreferences.getString("userEmail", "데이터 없음");

        // 텍스트 Gradient 적용
        TextView textView = v.findViewById(R.id.profileText);
        Tvg.change(textView, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        TextView mainNameTextView = v.findViewById(R.id.mainNameTextView);
        mainNameTextView.setText(userName);

        TextView nameTextView = v.findViewById(R.id.nameTextView);
        nameTextView.setText(userName);

        TextView emailTextView = v.findViewById(R.id.emailTextView);
        emailTextView.setText(userEmail);

        MainActivity activity = (MainActivity)getActivity();

        Button confirmButton = v.findViewById(R.id.confirmBtn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 비밀번호 확인 하기 //
                activity.changeFragment(3);
            }
        });

        return v;
    }
}
