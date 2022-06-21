package com.example.zaevtour.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.R;

public class ModifyProfileView extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.modify_profile_view, container, false);

        // 텍스트 Gradient 적용
        TextView textView = v.findViewById(R.id.profileText);
        Tvg.change(textView, Color.parseColor("#8CEDB3"), Color.parseColor("#6C92F4"));

        return v;
    }
}
