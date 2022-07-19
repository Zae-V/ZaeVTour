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

import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalenderFragment extends Fragment implements SlideDatePickerDialogCallback {

    private CalenderViewModel mViewModel;

    Button searchDateBtn;
    TextView startDateTextView;
    TextView endDateTextView;

    public static CalenderFragment newInstance() {
        return new CalenderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_calender, container, false);

        searchDateBtn = v.findViewById(R.id.searchDate);
        startDateTextView = v.findViewById(R.id.startDateTextView);
        endDateTextView = v.findViewById(R.id.endDateTextView);

        searchDateBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
                builder.setTitleText("Date Picker");

                //미리 날짜 선택
                builder.setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds()));

                MaterialDatePicker materialDatePicker = builder.build();

                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");
                //dialog.show(getActivity().getSupportFragmentManager(), "Dialog");

                //확인버튼
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                        Date date1 = new Date();
                        Date date2 = new Date();

                        date1.setTime(selection.first);
                        date2.setTime(selection.second);

                        String dateString1 = simpleDateFormat.format(date1);
                        String dateString2 = simpleDateFormat.format(date2);

                        endDateTextView.setText(dateString1 + "\n" + dateString2);

                        // fragment 이동
                        MainActivity activity = (MainActivity)getActivity() ;
                        activity.changeFragment(10);

                    }
                });
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

    }
}