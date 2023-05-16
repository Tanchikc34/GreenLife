package com.example.greenlife.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityHomeBinding;
import com.example.greenlife.ui.menu.MainActivityMenu;
import com.example.greenlife.ui.minigame.ActivityFlower;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivityHome extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensorLight;
    Dialog dialog;
    private float currentLightLevel = 0;
    private ActivityHomeBinding binding1;
    int[] ims = { R.drawable.image, R.drawable.image, R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8,
            R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12, R.drawable.image13};

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
                binding1.imageView5.setImageResource(ims[integer]);
            }
        });


        dialog = new Dialog(MainActivityHome.this);
        dialog.setContentView(R.layout.dialog_view);

        binding1.BookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
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
                    Intent intent = new Intent(MainActivityHome.this, ActivityFlower.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

    }

    /*
    @Override
    public void onPause() {
        super.onPause();
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }*/
}

