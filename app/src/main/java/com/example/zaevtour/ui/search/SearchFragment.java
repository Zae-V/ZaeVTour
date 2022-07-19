package com.example.zaevtour.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.zaevtour.MainActivity;
import com.example.zaevtour.PloggingActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.PlaceActivity;
import com.example.zaevtour.VeganActivity;
import com.example.zaevtour.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private String select;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainActivity activity = (MainActivity)getActivity() ;

        Button spinnerBtn = root.findViewById(R.id.spinnerBtn);

        final Observer<String> selectObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                spinnerBtn.setText(s);
            }
        };

        SharedViewModel searchViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        searchViewModel.getMessage().observe(getViewLifecycleOwner(), selectObserver);


        spinnerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                activity.changeFragment(4);
            }
        });

        SearchView searchBar = root.findViewById(R.id.searchBar);
        searchBar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                searchBar.setIconified(false);
            }
        });

        Button spotBtn = root.findViewById(R.id.spotBtn);
        spotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlaceActivity.class);
                startActivity(intent);
            }
        });

        Button ploggingBtn = root.findViewById(R.id.ploggingBtn);
        ploggingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PloggingActivity.class);
                startActivity(intent);
            }
        });

        Button restaurantBtn = root.findViewById(R.id.restaurantBtn);
        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), VeganActivity.class);
//                startActivity(intent);
                  activity.changeFragment(8);
                  //Go to Category_Restaurant

            }
        });

        // 임시로 연결 설정, 후에 수정사항
        Button allBtn = root.findViewById(R.id.allBtn);
        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(9);
            }
        });
//        final TextView textView = binding.textProfile;
//        searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // 임시로 연결 설정, 후에 수정사항
        Button shopBtn = root.findViewById(R.id.shopBtn);
        shopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(11);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
