package com.example.greenlife.ui.minigame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityMiniGameBinding;
import com.example.greenlife.ui.home.HomeViewModel;
import com.example.greenlife.ui.home.MainActivityHome;

public class ActivityMiniGame extends AppCompatActivity {

    private ActivityMiniGameBinding binding2;
    int flag = 0;
    int ROWS = 5;
    int COLUMNS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = DataBindingUtil.setContentView(this, R.layout.activity_mini_game);
        MiniGameViewModel miniGameViewModel = new ViewModelProvider(this).get(MiniGameViewModel.class);
        binding2.setViewModel(miniGameViewModel);
        binding2.setLifecycleOwner(this);

        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 265);
        for (int i = 0; i < ROWS; i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < COLUMNS; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.costomization_bee);
                //imageView.setBackgroundColor(Color.YELLOW);

                int a = i * COLUMNS + j;
                miniGameViewModel.getBeeLiveData(a).observe(this,
                        aBoolean -> imageView.setVisibility(aBoolean ?View.VISIBLE:View.INVISIBLE));


                imageView.setOnClickListener(view -> {
                    flag -= 1;
                    miniGameViewModel.killBee(a);
                    miniGameViewModel.addPollenClick();
                });
                imageView.setLayoutParams(params);
                tableRow.addView(imageView, j);
            }

            binding2.tableLayout.addView(tableRow, i);
        }


        miniGameViewModel.getCellLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                miniGameViewModel.setBee(integer);
                flag += 1;

                if (flag == 3){
                    miniGameViewModel.minusLife();
                }
                else if (flag == 6){
                    miniGameViewModel.minusLife();
                }
                else if (flag == 9){
                    miniGameViewModel.minusLife();
                }


                if (miniGameViewModel.getLifeLiveData().getValue().intValue() == 0){
                    miniGameViewModel.minusPollen();
                    Intent intent = new Intent(ActivityMiniGame.this, MainActivityHome.class);
                    startActivity(intent);
                    finish();}
            }
        });



        binding2.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(ActivityMiniGame.this, MainActivityHome.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        MiniGameViewModel miniGameViewModel = new ViewModelProvider(this).get(MiniGameViewModel.class);
        miniGameViewModel.upBd();
    }

}