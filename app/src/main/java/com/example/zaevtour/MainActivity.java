package com.example.zaevtour;

import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zaevtour.ui.home.HomeFragment;
import com.example.zaevtour.ui.profile.ModifyProfileView;
import com.example.zaevtour.ui.profile.ModifyProfileView2;
import com.example.zaevtour.ui.profile.ProfileFragment;
import com.example.zaevtour.ui.search.SearchFragment;
import com.example.zaevtour.ui.search.SearchFragment2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.zaevtour.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search,R.id.navigation_bookmark, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void changeFragment(int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        navView.setVisibility(View.VISIBLE);
        switch(index){
            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.nav_host_fragment_activity_main, profileFragment);
                transaction.commit();
                break;
            case 2:
                ModifyProfileView modifyProfileView = new ModifyProfileView();
                transaction.replace(R.id.nav_host_fragment_activity_main, modifyProfileView);
                transaction.commit();
                break;
            case 3:
                ModifyProfileView2 modifyProfileView2 = new ModifyProfileView2();
                transaction.replace(R.id.nav_host_fragment_activity_main, modifyProfileView2);
                transaction.commit();
                break;
            case 4:
                SearchFragment2 searchFragment2 = new SearchFragment2();
                transaction.replace(R.id.nav_host_fragment_activity_main,searchFragment2);
                navView.setVisibility(View.GONE);
                transaction.commit();
                break;
            case 5:
                SearchFragment searchFragment = new SearchFragment();
                transaction.replace(R.id.nav_host_fragment_activity_main,searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        List fragmentList = getSupportFragmentManager().getFragments();

        boolean handled = false;
        for(Object f : fragmentList) {
            if(f instanceof SearchFragment2) {
                handled = ((SearchFragment2)f).onBackPressed();

                if(handled) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    SearchFragment searchFragment = new SearchFragment();
                    transaction.replace(R.id.nav_host_fragment_activity_main,searchFragment);
                    transaction.commit();
                    navView.setVisibility(View.VISIBLE);

                    break;
                }
            }
        }

        if(!handled) {
            super.onBackPressed();
        }
    }

    public BottomNavigationView getNav() {
        return navView;
    }
}