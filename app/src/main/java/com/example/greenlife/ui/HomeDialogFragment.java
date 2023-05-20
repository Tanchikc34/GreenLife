package com.example.greenlife.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.example.greenlife.R;

public class HomeDialogFragment  extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Анемона")
                .setMessage("Анемона очень любит солнце")
                .setIcon(R.drawable.icon)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });
        return builder.create();
    }



}
