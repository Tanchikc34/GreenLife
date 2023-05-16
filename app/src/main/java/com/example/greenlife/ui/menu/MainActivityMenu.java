package com.example.greenlife.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.greenlife.R;
import com.example.greenlife.databinding.ActivityMenuBinding;
import com.example.greenlife.ui.home.MainActivityHome;

public class MainActivityMenu extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
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

    }

}