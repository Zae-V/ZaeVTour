package com.example.zaevtour.ui.bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentBookmarkBinding;

import java.util.ArrayList;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding binding;
    RecyclerView bookmarkRecyclerView;
    ListAdapter listAdapter;
    //ItemTouchHelperCallback helper;
    ArrayList<BookmarkItem> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BookmarkViewModel bookmarkViewModel =
                new ViewModelProvider(this).get(BookmarkViewModel.class);

        //final TextView textView = binding.bookmarkText;

        //bookmarkViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        View v =  inflater.inflate(R.layout.fragment_bookmark, container, false);

        //recyclerview
        bookmarkRecyclerView = (RecyclerView) v.findViewById(R.id.bookmarkRecycler);
        bookmarkRecyclerView.setHasFixedSize(true);
        listAdapter = new ListAdapter(items);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        bookmarkRecyclerView.setLayoutManager(mLayoutManager);
        bookmarkRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bookmarkRecyclerView.setAdapter(listAdapter);

        //ItemTouchHelper 생성
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(listAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(bookmarkRecyclerView);



        //Adapter에 데이터 추가
        BookmarkItem bookmarkItem1 = new BookmarkItem(R.drawable.vegan_burger,"제비식당","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem2 = new BookmarkItem(R.drawable.vegan_burger,"야채꼬치","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem3 = new BookmarkItem(R.drawable.vegan_burger,"비건버거","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem4 = new BookmarkItem(R.drawable.vegan_burger,"초록숲상점","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem5 = new BookmarkItem(R.drawable.vegan_burger,"제로웨이스트샵","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem6 = new BookmarkItem(R.drawable.vegan_burger,"제비식당","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem7 = new BookmarkItem(R.drawable.vegan_burger,"야채꼬치","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem8 = new BookmarkItem(R.drawable.vegan_burger,"비건버거","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem9 = new BookmarkItem(R.drawable.vegan_burger,"초록숲상점","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");
        BookmarkItem bookmarkItem10 = new BookmarkItem(R.drawable.vegan_burger,"제로웨이스트샵","서울시 강서구 개화동", "AM 9:00 ~ PM 10:00");

        listAdapter.addItem(bookmarkItem1);
        listAdapter.addItem(bookmarkItem2);
        listAdapter.addItem(bookmarkItem3);
        listAdapter.addItem(bookmarkItem4);
        listAdapter.addItem(bookmarkItem5);
        listAdapter.addItem(bookmarkItem6);
        listAdapter.addItem(bookmarkItem7);
        listAdapter.addItem(bookmarkItem8);
        listAdapter.addItem(bookmarkItem9);
        listAdapter.addItem(bookmarkItem10);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}