package com.example.zaevtour.ui.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.captaindroid.tvg.Tvg;
import com.example.zaevtour.IntroActivity;
import com.example.zaevtour.MainActivity;
import com.example.zaevtour.MySharedPreferences;
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentProfileBinding;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    String userName;
    String userEmail;
    String userProfileImage;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        userName = MySharedPreferences.getUserName(getActivity().getApplicationContext());
        userEmail = MySharedPreferences.getUserEmail(getActivity().getApplicationContext());
        userProfileImage = MySharedPreferences.getUserProfileImage(getActivity().getApplicationContext());

        // 텍스트 Gradient 적용
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView profileTextView = v.findViewById(R.id.profileText);
        Tvg.change(profileTextView, Color.parseColor("#6C92F4"),  Color.parseColor("#41E884"));

        TextView mainNameTextView = v.findViewById(R.id.mainNameTextView);
        mainNameTextView.setText(userName);

        TextView nameTextView = v.findViewById(R.id.nameTextView);
        nameTextView.setText(userName);

        TextView emailTextView = v.findViewById(R.id.emailTextView);
        emailTextView.setText(userEmail);

        CircleImageView userProfileImageView = v.findViewById(R.id.profileImageView);

        Glide.with(this)
                .load(userProfileImage)
                .placeholder(R.drawable.default_profile_image)
                .error(R.drawable.default_profile_image)
                .fallback(R.drawable.default_profile_image)
                .into(userProfileImageView);

        MainActivity activity = (MainActivity)getActivity();

        TextView modifyProfileTextView = v.findViewById(R.id.modifyProfileTextView);
        modifyProfileTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(2);
            }
        });

        View logoutTextView = v.findViewById(R.id.logoutTextView);
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        
        return v;
    }

    public void showDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setIcon(R.mipmap.ic_launcher);//알림창 아이콘 설정
        dialog.setMessage("로그아웃 하시겠습니까?"); //알림창 메세지 설정

        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"로그아웃 되었습니다!",Toast.LENGTH_SHORT).show();
                MySharedPreferences.clearUser(getActivity());
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("아니오",null);
        dialog.show();
    }
}