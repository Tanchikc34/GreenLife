package com.example.greenlife.ui.minigame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityMiniGameBinding;
import com.example.greenlife.ui.home.HomeViewModel;
import com.example.greenlife.ui.home.MainActivityHome;
import com.example.greenlife.ui.menu.MainActivityMenu;

public class ActivityFlower extends AppCompatActivity {

    private ActivityMiniGameBinding binding2;
    int flag = 0;
    //int[] ims = { R.drawable.image, R.drawable.image, R.drawable.image1, R.drawable.image2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = DataBindingUtil.setContentView(this, R.layout.activity_mini_game);

        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        FlowerViewModel flowerViewModel = new ViewModelProvider(this).get(FlowerViewModel.class);

        flowerViewModel.getCellLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer){
                    case (1):
                        binding2.Cell1.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (2):
                        binding2.Cell2.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (3):
                        binding2.Cell3.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (4):
                        binding2.Cell4.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (5):
                        binding2.Cell5.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (6):
                        binding2.Cell6.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (7):
                        binding2.Cell7.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (8):
                        binding2.Cell8.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (9):
                        binding2.Cell9.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (10):
                        binding2.Cell10.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (12):
                        binding2.Cell12.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (13):
                        binding2.Cell13.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (14):
                        binding2.Cell14.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (15):
                        binding2.Cell15.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (16):
                        binding2.Cell16.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (17):
                        binding2.Cell17.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (18):
                        binding2.Cell18.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (19):
                        binding2.Cell19.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                    case (20):
                        binding2.Cell20.setVisibility(View.VISIBLE);
                        flag += 1;
                        break;
                }
            }
        });

        if (flag > 3){
            flowerViewModel.minusLife();
        }

        binding2.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ActivityFlower.this, MainActivityHome.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}