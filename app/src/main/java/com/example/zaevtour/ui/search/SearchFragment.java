package com.example.zaevtour.ui.search;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zaevtour.MainActivity;
import com.example.zaevtour.R;
import com.example.zaevtour.databinding.FragmentProfileBinding;
import com.example.zaevtour.databinding.FragmentSearchBinding;
import com.example.zaevtour.ui.search.Adapter.CityAdapter;
import com.example.zaevtour.ui.search.Adapter.VisitedAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainActivity activity = (MainActivity)getActivity();
        if (getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).getNav().setVisibility(View.VISIBLE);
        }

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        binding.spinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.changeFragment(4);
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
