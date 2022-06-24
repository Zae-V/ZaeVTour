package com.example.zaevtour.ui.search;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

        View v = inflater.inflate(R.layout.fragment_search, container, false);

        MainActivity activity = (MainActivity)getActivity();

        ImageView imageView = v.findViewById(R.id.backBtn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"click",Toast.LENGTH_LONG).show();
            }
        });

        if (getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).getNav().setVisibility(View.GONE);
        }

        String[] CityName = {"전체","강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구",
        "동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","종구","중랑구"};


        String[] Visited = {"강남구","관악구","구로구"};
        CityAdapter cityAdapter = new CityAdapter((getContext()),CityName);
        binding.allList.setAdapter(cityAdapter);
        binding.allList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),CityName[i]+"을 선택하셨습니다.", Toast.LENGTH_LONG).show();
            }
        });

        VisitedAdapter recentCityAdapter = new VisitedAdapter(getContext(),Visited);
        binding.recentVisit.setAdapter(recentCityAdapter);
        binding.recentVisit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),Visited[i] +"을 선택하셨습니다.", Toast.LENGTH_LONG).show();
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
