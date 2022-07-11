package com.example.zaevtour.ui.travel;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentTravelBinding;
import com.example.zaevtour.ui.bookmark.ItemTouchHelperCallback;


import java.util.ArrayList;

public class TravelFragment extends Fragment {

    private FragmentTravelBinding binding;
    RecyclerView scheduleRecyclerView;
    TravelListAdapter listAdapter;
    //ItemTouchHelperCallback helper;
    ArrayList<TravelItem> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TravelViewModel TravelViewModel =
                new ViewModelProvider(this).get(TravelViewModel.class);

        //final TextView textView = binding.bookmarkText;

        //bookmarkViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        View v =  inflater.inflate(R.layout.fragment_travel, container, false);

        //recyclerview
        scheduleRecyclerView = (RecyclerView) v.findViewById(R.id.travelRecycler);
        scheduleRecyclerView.setHasFixedSize(true);
        listAdapter = new TravelListAdapter(items);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        scheduleRecyclerView.setLayoutManager(mLayoutManager);
        scheduleRecyclerView.setItemAnimator(new DefaultItemAnimator());
        scheduleRecyclerView.setAdapter(listAdapter);

        //ItemTouchHelper 생성
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(listAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(scheduleRecyclerView);



        //Adapter에 데이터 추가
        TravelItem item1 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행1");
        TravelItem item2 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행2");
        TravelItem item3 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행3");
        TravelItem item4 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행4");
        TravelItem item5 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행5");
        TravelItem item6 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행6");
        TravelItem item7 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행7");
        TravelItem item8 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행8");
        TravelItem item9 = new TravelItem(R.drawable.profile_img,"2020.04.14 ~ 2020.05.15", "ㅇㅇ여행9");



        listAdapter.addItem(item1);
        listAdapter.addItem(item2);
        listAdapter.addItem(item3);
        listAdapter.addItem(item4);
        listAdapter.addItem(item5);
        listAdapter.addItem(item6);
        listAdapter.addItem(item7);
        listAdapter.addItem(item8);
        listAdapter.addItem(item9);
//        listAdapter.addItem(bookmarkItem3);
//        listAdapter.addItem(bookmarkItem4);
//        listAdapter.addItem(bookmarkItem5);

        listAdapter.setOnItemClickListener(new OnTravelItemClickListener() {
            @Override
            public void onItemClick(TravelListAdapter.ItemViewHolder holder, View view, int position) {
                TravelItem item = listAdapter.getItem(position);
                Toast.makeText(getActivity(),"아이템 선택 " + item.getName(),
                        Toast.LENGTH_SHORT).show();
                MainActivity activity = (MainActivity)getActivity() ;
                activity.changeFragment(6);
            }

        });
        return v;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}