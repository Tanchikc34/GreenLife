package com.example.greenlife;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.greenlife.databinding.ActivityHomeBinding;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivityHome extends AppCompatActivity {

    private ActivityHomeBinding binding1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding1 = DataBindingUtil.setContentView(this, R.layout.activity_home);
        //PlantImage plant = new PlantImage("Анемона");
        //binding1.setPlant(plant);

        int i = 0;
        int[] drawable = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

        PlantImage.getInstance().getliveLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        binding1.textView2.setText(integer.toString());
                        PlantImage.getInstance().mClick();
                    }
                }, 0, 3, TimeUnit.SECONDS);
            }
        });
        binding1.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlantImage.getInstance().addClick();
            }
        });
    }
}