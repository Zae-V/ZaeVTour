package com.example.zaevtour.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zaevtour.MainActivity;
import com.example.zaevtour.PloggingActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.PlaceActivity;
import com.example.zaevtour.VeganActivity;
import com.example.zaevtour.databinding.FragmentSearchBinding;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainActivity activity = (MainActivity)getActivity() ;

        Button spinnerBtn = root.findViewById(R.id.spinnerBtn);
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
                Intent intent = new Intent(getActivity(), VeganActivity.class);
                startActivity(intent);
            }
        });
//        final TextView textView = binding.textProfile;
//        searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
