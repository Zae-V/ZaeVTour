package com.example.zaevtour;

import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.util.Log;

import com.example.zaevtour.ui.home.HomeFragment;
import com.example.zaevtour.ui.profile.ModifyProfileView;
import com.example.zaevtour.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.zaevtour.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search,R.id.navigation_bookmark, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void changeFragment(int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch(index){
            case 1:
                ModifyProfileView modifyProfileView = new ModifyProfileView();
                transaction.replace(R.id.nav_host_fragment_activity_main, modifyProfileView);
                transaction.commit();
                break;
            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                transaction.replace(R.id.nav_host_fragment_activity_main, profileFragment);
                transaction.commit();
                break;
        }
    }

}