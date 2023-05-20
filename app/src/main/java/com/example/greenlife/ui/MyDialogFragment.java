package com.example.greenlife.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.greenlife.App;
import com.example.greenlife.R;
import com.example.greenlife.db.AppDatabase;
import com.example.greenlife.db.dao.PlantInfoDao;

public class MyDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Важное сообщение!")
                .setMessage("Спасите цветочек от ос!")
                .setIcon(R.drawable.bee)
                .setPositiveButton("ОК, бегу за мухобойкой", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });
        return builder.create();
    }



}
