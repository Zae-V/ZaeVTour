package com.example.zaevtour.ui.sign;

import androidx.fragment.app.FragmentTransaction;
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
import com.example.zaevtour.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInFragment extends Fragment {

    TextView userGreetingText;

    TextView findPWText;
    TextView signUpText;

    EditText editID;
    EditText editPW;

    Button signInBtn;
    TextView msg;

    private FirebaseAuth mAuth;

    private SignInViewModel mViewModel;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sign_in, container, false);

        userGreetingText = v.findViewById(R.id.userGreetingText);
        findPWText = v.findViewById(R.id.findPwText);
        signUpText = v.findViewById(R.id.signUpText);

        editID = v.findViewById(R.id.editID);
        editPW = v.findViewById(R.id.editPW);

        signInBtn = v.findViewById(R.id.signInBtn);
        msg = v.findViewById(R.id.error_message);

        mAuth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editID.getText().toString().trim();
                String pwd = editPW.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            msg.setText("이메일과 비밀번호를 확인해주십시오.");
                        }
                    }
                });

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
        Tvg.change(userGreetingText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
    }

}