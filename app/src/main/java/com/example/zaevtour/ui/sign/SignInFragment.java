package com.example.zaevtour.ui.sign;

import static android.content.Context.MODE_PRIVATE;

import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.IntroActivity;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.MySharedPreferences;
import com.example.zaevtour.R;
import com.example.zaevtour.SignActivity;
import com.example.zaevtour.Users;
import com.example.zaevtour.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.auth.SignInMethodQueryResult;


import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SignInFragment extends Fragment {

    TextView userGreetingText;

    TextView findPWText;
    TextView signUpText;

    EditText editID;
    EditText editPW;

    Button signInBtn;
    TextView msg;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    private SignInViewModel mViewModel;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);

        userGreetingText = v.findViewById(R.id.userGreetingText);
        findPWText = v.findViewById(R.id.findPwText);
        signUpText = v.findViewById(R.id.signUpText);

        editID = v.findViewById(R.id.editID);
        editPW = v.findViewById(R.id.editPW);

        signInBtn = v.findViewById(R.id.signInBtn);
        msg = v.findViewById(R.id.error_message);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editID.getText().toString().trim();
                String pwd = editPW.getText().toString().trim();

                if (!email.equals("") && !pwd.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (!Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()) {
                                    msg.setText("????????? ????????? ?????????????????????.");
                                } else {
                                    ArrayList bookmarkList = new ArrayList();
                                    ArrayList currentPosition = new ArrayList();
                                    String profileImage = new String();

                                    mFirestore.collection("User").document(email)
                                            .get().
                                            addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        HashMap userInfo = (HashMap) document.getData();
                                                        String userName = (String) userInfo.get("userName");

                                                        Users user = new Users(userName, email, bookmarkList, currentPosition, profileImage, false);
                                                        MySharedPreferences.saveUserInfo(getActivity().getApplicationContext(), user);

                                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                                        startActivity(intent);

                                                    } else {
                                                        Log.d("ERROR", "get failed with ", task.getException());
                                                    }
                                                }
                                            });
                                }
                            } else {
                                mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                        if (!task.isSuccessful()) {
                                            msg.setText("???????????? ?????? ??????????????????.");
                                        } else {
                                            msg.setText("??????????????? ?????????????????????.");

                                        }
                                    }
                                });
                            }
                        }
                    });
                }
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
                ((SignActivity) getActivity()).replaceFragment(SignUpFragment.newInstance()); // ?????? ????????? Fragment??? Instance??? Main?????? ??????
            }
        });

        // ????????? ????????? ??????????????? ??????
        Tvg.change(userGreetingText, Color.parseColor("#6C92F4"), Color.parseColor("#41E884"));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        // TODO: Use the ViewModel
    }

}
