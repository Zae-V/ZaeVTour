package com.example.zaevtour;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.captaindroid.tvg.Tvg;

public class SignActivity extends AppCompatActivity {

    TextView usernameText;
    TextView greetingText;
    EditText editID;
    EditText editPW;

    Button signInBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_in);

        usernameText = findViewById(R.id.usernameText);
        greetingText = findViewById(R.id.greetingText);

        editID = findViewById(R.id.editID);
        editPW = findViewById(R.id.editPW);

        signInBtn = findViewById(R.id.signInBtn);

        // 텍스트 글자색 그라데이션 적용
        Tvg.change(usernameText, Color.parseColor("@color/text_gradient_start"),  Color.parseColor("@color/text_gradient_end"));
        Tvg.change(greetingText, Color.parseColor("@color/text_gradient_start"),  Color.parseColor("@color/text_gradient_end"));



    }
}
