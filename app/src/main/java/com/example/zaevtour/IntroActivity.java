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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class IntroActivity extends AppCompatActivity {
    private static final String TAG = "로그인";
    private FirebaseAuth mAuth;

    Animation fadeIn;
    TextView introText;
    Button kakaoJoinBtn;
    Button joinBtn;
    ImageView introImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        introText = findViewById(R.id.introText);
        introImg = findViewById(R.id.introImg);
        Glide.with(this).load(R.raw.animation_bird).into(introImg);
        kakaoJoinBtn = findViewById(R.id.kakaoJoinBtn);
        joinBtn = findViewById(R.id.joinBtn);

        mAuth = FirebaseAuth.getInstance();

        // 제비 글자색 바꾸기
        String content = introText.getText().toString();
        SpannableString spannableString = new SpannableString(content);

        String word1 = "제";
        String word2 = "비";

        int loc1 = content.indexOf(word1);
        int loc2 = content.indexOf(word2);

        Log.d("MYTAG", String.valueOf(loc1));
        Log.d("MYTAG", String.valueOf(loc2));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#47FFC5")),
                loc1, loc1 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#47FFC5")),
                loc2, loc2 + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        introText.setText(spannableString);

        // fade in 애니메이션 적용
        fadeIn= AnimationUtils.loadAnimation(this,R.anim.fadein);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        introText.startAnimation(fadeIn);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if (user != null) {
                                Log.d(TAG, "invoke: id=" + user.getId());
                                Log.d(TAG, "invoke: email=" + user.getKakaoAccount().getEmail());

                                String email = user.getKakaoAccount().getEmail();
                                String password = user.getId() + user.getKakaoAccount().getEmail();

                                // 이메일 중복 체크 필요
                                // 이메일 중복 O -> 로그인 시도
                                    // 1. 성공
                                    // 2. 실패 - 다른 사람의 아이디 ->  이미 가입된 이메일이 있습니다.
                                // 이메일 중복 X
                                    // 계정 생성

                                signUp(email, password);

                            }
                            if (throwable != null) {
                                Log.d(TAG, "invoke: " + throwable.getLocalizedMessage());
                            }
                            return null;
                        }
                    });
                }
                if (throwable != null) {
                    // TBD
                    Log.d(TAG, "invoke: " + throwable.getLocalizedMessage());
                }
                return null;
            }
        };

        kakaoJoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(IntroActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(IntroActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(IntroActivity.this, callback);
                }
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(IntroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "로그인 성공");
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Log.d(TAG, "로그인 실패");
                    Log.d(TAG, String.valueOf(task.getException()));
                }
            }
        });
    }

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(IntroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "회원가입 성공");
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "회원가입 실패");
                    Log.d(TAG, String.valueOf(task.getException()));

                    Log.d(TAG, "로그인 시도");
                    signIn(email, password);
                }
            }
        });
    }
}
