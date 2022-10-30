package com.example.thibanglai;

import static com.example.thibanglai.setting.MyApplication.nameSharedPreference;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.thibanglai.database.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thibanglai.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    boolean isFirstRun;
    SharedPreferences.Editor editor;
    DataBaseHelper database = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            Khoi_tao();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_save)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Intent intent = getIntent();
        int tab = intent.getIntExtra("tab",0);
        setDefault(tab);
    }
    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }
    public void setDefault(int tab){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        if(tab==0){
            return;
        }else if(tab==1){
            navView.setSelectedItemId(R.id.navigation_search);
        }else {
            navView.setSelectedItemId(R.id.navigation_save);
        }

    }
    private void Khoi_tao() throws IOException {
        sharedPreferences = this.getSharedPreferences(nameSharedPreference, Context.MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun",true);
        if(isFirstRun){
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstRun",false);
            editor.apply();
            database.createDatabase();
        }
    }
}