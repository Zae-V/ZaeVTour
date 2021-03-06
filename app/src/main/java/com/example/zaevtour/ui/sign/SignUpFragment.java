package com.example.zaevtour.ui.sign;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.IntroActivity;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.MySharedPreferences;
import com.example.zaevtour.R;
import com.example.zaevtour.SignActivity;
import com.example.zaevtour.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SignUpFragment extends Fragment {

    TextView greetingText;
    EditText editUsernameEnter;
    EditText editEmailEnter;
    EditText editPWEnter;
    EditText editPWCheck;
    TextView msg;
    private SignUpViewModel mViewModel;
    android.widget.Button Button;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;


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
        msg = v.findViewById(R.id.error_message);

        // ????????? ????????? ??????????????? ??????
        Tvg.change(greetingText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        Button = v.findViewById(R.id.signUpBtn);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editPWEnter.getText().toString().trim();
                String pwCheck = editPWCheck.getText().toString().trim();
                String username = editUsernameEnter.getText().toString().trim();
                String email = editEmailEnter.getText().toString().trim();

                ArrayList bookmarkList = new ArrayList();
                ArrayList currentPosition = new ArrayList();
                String profileImage = new String();

                if(username.length() == 0 || email.length() == 0 || pwCheck.length() == 0 || password.length() == 0){
                    msg.setText("????????? ?????? ??????????????????.");
                }
                else if(password.equals(pwCheck)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getActivity(),"?????? ???????????? ?????????????????????.",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                Users userInfo = new Users(username, email, bookmarkList, currentPosition, profileImage,false);

                                mFirestore.collection("User").document(userInfo.userEmail).set(userInfo);
                                MySharedPreferences.saveUserInfo(getActivity().getApplicationContext(), userInfo);

                                Intent intent = new Intent(getActivity(), SignActivity.class);
                                startActivity(intent);
                            } else {
                                if(password.length()<6){
                                    msg.setText("??????????????? 6?????? ???????????? ??????????????????.");
                                }
                                else if( task.getException() instanceof FirebaseAuthUserCollisionException){
                                    msg.setText("?????? ?????????????????? ??????????????????.");
                                }else if(!emailFormatCheck(email)){
                                    msg.setText("???????????? ????????? ???????????? ??????????????????.");
                                }else{
                                    msg.setText("???????????? ????????? ??????????????????. ????????? ?????? ??????????????????.");
                                }
                            }
                        }
                    });
                }else{
                    msg.setText("??????????????? ?????? ?????????????????????.");
                }
            }
        });

        return v;
    }

    public Boolean emailFormatCheck(String inputEmail){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(inputEmail.matches(emailPattern)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        // TODO: Use the ViewModel
    }

}