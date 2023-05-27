package com.example.greenlife.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityMenuBinding;
import com.example.greenlife.ui.home.MainActivityHome;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivityMenu extends AppCompatActivity {

    private ActivityMenuBinding binding;
    private MenuViewModel menuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);

        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(menuViewModel);

        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        binding.view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivityMenu.this, MainActivityHome.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        binding.BookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MenuDialogBook myMenuDialogBook = new MenuDialogBook();
                    FragmentManager manager = getSupportFragmentManager();

                    FragmentTransaction transaction = manager.beginTransaction();
                    myMenuDialogBook.show(transaction, "dialog");

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                menuViewModel.updateTime();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

}