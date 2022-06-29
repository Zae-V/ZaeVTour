package com.example.zaevtour.ui.bookmark;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentBookmarkBinding;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding binding;
    RecyclerView bookmarkRecyclerView;
    ListAdapter listAdapter;
    ItemTouchHelperCallback helper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel bookmarkViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.bookmarkText;

        bookmarkViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Set the adapter

        if (root instanceof RecyclerView) {


            Context context = root.getContext();

            RecyclerView mRecyclerView = (RecyclerView) root;


            mRecyclerView.setHasFixedSize(true);


            // use a linear layout manager

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);

            mRecyclerView.setLayoutManager(mLayoutManager);


            // specify an adapter (see also next example)

            listAdapter = new ListAdapter(listAdapter.items);

            mRecyclerView.setAdapter(listAdapter);

        }

        //ItemTouchHelper 생성
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(listAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(bookmarkRecyclerView);



        //Adapter에 데이터 추가
        Restaurant restaurant1 = new Restaurant(R.drawable.vegan_burger,"제비식당","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        Restaurant restaurant2 = new Restaurant(R.drawable.vegan_burger,"야채꼬치","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        Restaurant restaurant3 = new Restaurant(R.drawable.vegan_burger,"비건버거","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        Restaurant restaurant4 = new Restaurant(R.drawable.vegan_burger,"초록숲상점","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        Restaurant restaurant5 = new Restaurant(R.drawable.vegan_burger,"제로웨이스트샵","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");

        listAdapter.addItem(restaurant1);
        listAdapter.addItem(restaurant2);
        listAdapter.addItem(restaurant3);
        listAdapter.addItem(restaurant4);
        listAdapter.addItem(restaurant5);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}