package com.example.greenlife.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityHomeBinding;
import com.example.greenlife.ui.magazin.MagazinDialogFragment;
import com.example.greenlife.ui.menu.MainActivityMenu;
import com.example.greenlife.ui.minigame.ActivityMiniGame;
import com.example.greenlife.ui.minigame.MiniGameViewModel;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivityHome extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensorLight;
    private float currentLightLevel = 0;
    private ActivityHomeBinding binding1;
    int[] ims = {R.drawable.image01, R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
            R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13};

    int[] imsPlant = {R.drawable.image_color, R.drawable.image_color5, R.drawable.image_color3, R.drawable.image_color4,
            R.drawable.image_color2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding1 = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding1.setViewModel(homeViewModel);
        binding1.setLifecycleOwner(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        homeViewModel.getImLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding1.imageView8.setImageResource(ims[integer]);
            }
        });

        SensorEventListener listenerLight = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                currentLightLevel = event.values[0];
            }
        };
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                homeViewModel.setLightLevel(currentLightLevel);
            }
        }, 0, 1, TimeUnit.SECONDS);
        sensorManager.registerListener(listenerLight, sensorLight, SensorManager.SENSOR_STATUS_ACCURACY_LOW);


        homeViewModel.getHpLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer > 35 && integer < 65){
                    binding1.imageView8.setColorFilter(Color.argb(100, 56, 25, 9));
                }
                else if (integer < 35){
                    binding1.imageView8.setColorFilter(Color.argb(100, 0, 0, 0));
                }
                else if (integer > 65){
                    binding1.imageView8.setColorFilter(null);
                }
                if (integer == 0){
                    homeViewModel.nullBd(); //  конец игры
                    try {
                        HomeDialogGameOver myHomeDialogGameOver = new HomeDialogGameOver();
                        FragmentManager manager = getSupportFragmentManager();

                        FragmentTransaction transaction = manager.beginTransaction();
                        myHomeDialogGameOver.show(transaction, "dialog");

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        binding1.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivityHome.this, MainActivityMenu.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        binding1.GameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivityHome.this, ActivityMiniGame.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        binding1.BookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HomeDialogFragment myDialogFragment = new HomeDialogFragment();
                    FragmentManager manager = getSupportFragmentManager();

                    FragmentTransaction transaction = manager.beginTransaction();
                    myDialogFragment.show(transaction, "dialog");

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        binding1.PencilsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MagazinDialogFragment myDialogFragment = new MagazinDialogFragment();
                    FragmentManager manager = getSupportFragmentManager();

                    FragmentTransaction transaction = manager.beginTransaction();
                    myDialogFragment.show(transaction, "dialog");


                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    public void changedPlant(Integer integer) {
        binding1.imageView6.setImageResource(imsPlant[integer]);
    }

    @Override
    public void onPause() {
        super.onPause();
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.upBd();
    }
}

