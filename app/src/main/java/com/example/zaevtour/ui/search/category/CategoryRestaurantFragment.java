package com.example.zaevtour.ui.search.category;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zaevtour.R;
import com.example.zaevtour.ui.search.category.CategoryAdapter;

import java.util.ArrayList;

public class CategoryRestaurantFragment extends Fragment {

    private ArrayList<CategoryRestaurant> restaurantsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private CategoryRestaurantViewModel mViewModel;

    public static CategoryRestaurantFragment newInstance() {
        return new CategoryRestaurantFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_category_restaurant, container, false);

        //recyclerview
        recyclerView = (RecyclerView) v.findViewById(R.id.categoryRecyclerView_restaurant);
        recyclerView.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(restaurantsList);

        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager
                = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(categoryAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
    }

    //데이터 준비(최종적으로는 동적으로 추가하거나 삭제할 수 있어야 한다. 이 데이터를 어디에 저장할지 정해야 한다.)
    private void prepareData() {
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
        restaurantsList.add(new CategoryRestaurant(R.drawable.vegan_burger,"비건 버거","버섯 버거, 콩 버거"));
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryRestaurantViewModel.class);
        // TODO: Use the ViewModel
    }

}