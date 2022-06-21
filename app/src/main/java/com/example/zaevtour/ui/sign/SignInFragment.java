package com.example.zaevtour.ui.sign;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.IntroActivity;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.SignActivity;

public class SignInFragment extends Fragment {

    TextView usernameText;
    TextView greetingText;

    TextView findPWText;
    TextView signUpText;

    EditText editID;
    EditText editPW;

    Button signInBtn;

    private SignInViewModel mViewModel;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        usernameText = v.findViewById(R.id.usernameText);
        greetingText = v.findViewById(R.id.greetingText);
        findPWText = v.findViewById(R.id.findPwText);
        signUpText = v.findViewById(R.id.signUpText);

        editID = v.findViewById(R.id.editID);
        editPW = v.findViewById(R.id.editPW);

        signInBtn = v.findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findPWText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SignActivity)getActivity()).replaceFragment(SignUpFragment.newInstance()); // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        // 텍스트 글자색 그라데이션 적용
        Tvg.change(usernameText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));
        Tvg.change(greetingText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
    }

}