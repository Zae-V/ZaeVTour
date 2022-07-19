package com.example.zaevtour.ui.schedule;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.util.Pair;

import com.example.zaevtour.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalenderFragment extends Fragment implements SlideDatePickerDialogCallback {

    private CalenderViewModel mViewModel;

    Button searchDateBtn;
    TextView dateTextView;

    public static CalenderFragment newInstance() {
        return new CalenderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_calender, container, false);

        searchDateBtn = v.findViewById(R.id.searchDate);
        dateTextView = v.findViewById(R.id.dateTextView);

        searchDateBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, 2100);

                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(endDate);
                builder.setLocale((Locale.KOREAN));
                builder.setThemeColor(Color.rgb(100, 200, 255));
                builder.setShowYear(true);
                builder.setCancelText("취소");
                builder.setConfirmText("확인"); //확인버튼 문자열

                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getActivity().getSupportFragmentManager(), "Dialog");
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalenderViewModel.class);
        // TODO: Use the ViewModel
    }

    //확인 버튼 눌렀을 때 실행되는 이벤트
    @Override
    public void onPositiveClick(int day, int month, int year, @NonNull Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault());

        dateTextView.setText(format.format(calendar.getTime()));
    }
}