package com.example.greenlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.greenlife.databinding.ActivityHomeBinding;
public class MainActivityMenu extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivityMenu.this, MainActivityHome.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        PlantImage plant = new PlantImage("Анемона");
        binding.setPlant(plant);
    }
}