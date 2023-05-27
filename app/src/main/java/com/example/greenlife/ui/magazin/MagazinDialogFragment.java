package com.example.greenlife.ui.magazin;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.greenlife.R;
import com.example.greenlife.databinding.MagazinLayoutBinding;
import com.example.greenlife.ui.home.HomeViewModel;
import com.example.greenlife.ui.home.MainActivityHome;
import com.example.greenlife.ui.minigame.MiniGameViewModel;

import java.util.ArrayList;

public class MagazinDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        MiniGameViewModel miniGameViewModel = new ViewModelProvider(this).get(MiniGameViewModel.class);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        MagazinLayoutBinding binding = MagazinLayoutBinding.inflate(getActivity().getLayoutInflater());
        builder.setView(binding.getRoot());
        // начальная инициализация списка
        ArrayList<State> states = new ArrayList<>();
        states.add(new State ("Цвет горшка \"Лето\" (красный, бежевый, оранжевый) Цена - 5 п.", R.drawable.plant_color1));
        states.add(new State ("Цвет горшка \"Море\" (голубой, тёмно-синий) Цена - 5 п.", R.drawable.plant_color2));
        states.add(new State ("Цвет горшка \"Пляж\" (бордовый, оранжевый) Цена - 5 п.", R.drawable.plant_color3));
        states.add(new State ("Цвет горшка \"Морской\" Цена - 5 п.", R.drawable.plant_color4));
        states.add(new State ("Цвет горшка \"Фиолетовый\" Цена - 5 п.", R.drawable.plant_color5));
        states.add(new State ("Удобрение (+2 к жизни растения) Цена - 5 п.", R.drawable.plant_dressing));

        // определяем слушателя нажатия элемента в списке
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(State state, int position) {
                Toast.makeText(getContext(), "Был выбран пункт " + state.getName(),
                        Toast.LENGTH_SHORT).show();
                if (position != 5 && miniGameViewModel.getPollenLiveData().getValue().intValue() != 0){
                    ((MainActivityHome) getActivity()).changedPlant(position);
                    miniGameViewModel.minusPollen();
                }
                if (position == 5 && miniGameViewModel.getPollenLiveData().getValue().intValue() != 0){
                    homeViewModel.addHp(2);
                    miniGameViewModel.minusPollen();
                }
            }
        };

        // создаем адаптер
        StateAdapter adapter = new StateAdapter(getLayoutInflater(), states, stateClickListener);
        // устанавливаем для списка адаптер
        binding.list.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.list.setAdapter(adapter);

        return builder.create();
    }

}
