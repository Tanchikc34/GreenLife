package com.example.greenlife.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityHomeBinding;

public class MainActivityHome extends AppCompatActivity {

    Dialog dialog;
    private ActivityHomeBinding binding1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding1 = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        homeViewModel.getWetnessLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding1.textView2.setText(integer.toString());
            }
        });


        binding1.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.addWetness();
            }
        });

        dialog = new Dialog(MainActivityHome.this);
        // Cсылкa на разметку
        dialog.setContentView(R.layout.dialog_view);

        binding1.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}