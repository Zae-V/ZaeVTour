package com.example.zaevtour.ui.sign;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.R;

public class SignUpFragment extends Fragment {

    TextView greetingText;
    EditText editUsernameEnter;
    EditText editEmailEnter;
    EditText editPWEnter;
    EditText editPWCheck;
    private SignUpViewModel mViewModel;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sign_up, container, false);

        greetingText = v.findViewById(R.id.greetingText);
        editUsernameEnter = v.findViewById(R.id.editUsernameEnter);
        editEmailEnter =v.findViewById(R.id.editEmailEnter);
        editPWEnter = v.findViewById(R.id.editPWEnter);
        editPWCheck =v.findViewById(R.id.editPWCheck);


        // 텍스트 글자색 그라데이션 적용
        Tvg.change(greetingText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
    }

}