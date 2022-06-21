package com.example.zaevtour.ui.profile;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
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
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        // 텍스트 Gradient 적용
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView textView = v.findViewById(R.id.profileText);
//        Tvg.change(textView, Color.parseColor("#8CEDB3"), Color.parseColor("#6C92F4"));

        return v;
    }

}