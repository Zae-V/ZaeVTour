package com.example.zaevtour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.databinding.ActivityMainBinding;

public class SignActivity extends AppCompatActivity {

    TextView usernameText;
    TextView greetingText;

    EditText editID;
    EditText editPW;

    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_in);

        usernameText = findViewById(R.id.usernameText);
        greetingText = findViewById(R.id.greetingText);

        editID = findViewById(R.id.editID);
        editPW = findViewById(R.id.editPW);

        signInBtn = findViewById(R.id.signInBtn);

        // 텍스트 글자색 그라데이션 적용
        Tvg.change(usernameText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));
        Tvg.change(greetingText, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));



    }
}
