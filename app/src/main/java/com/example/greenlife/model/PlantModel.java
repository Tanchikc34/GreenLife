package com.example.greenlife.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class PlantModel {
    public final static int MAX_VALUE = 100;
    public final static int MIN_VALUE = 0;

    private int wetness; //влага
    private int hp;
    private int happy;
    private int im;

    private MutableLiveData<Integer> wetnessData;
    private MutableLiveData<Integer> hpData;
    private MutableLiveData<Integer> happyData;
    private MutableLiveData<Integer> imData;

    public PlantModel(){
        wetness = MAX_VALUE;
        hp = MAX_VALUE;
        happy = MAX_VALUE;
        im = 0;
        wetnessData = new MutableLiveData<>(wetness);
        hpData = new MutableLiveData<>(hp);
        happyData = new MutableLiveData<>(happy);
        imData = new MutableLiveData<>(im);
    }

    public LiveData<Integer> getWetnessLiveData() {
        return wetnessData;
    }

    public LiveData<Integer> getHpLiveData() {
        return hpData;
    }

    public LiveData<Integer> getHappyLiveData() {
        return happyData;
    }

    public LiveData<Integer> getImLiveData() {
        return imData;
    }

    public void changeIm(){
        if (im < 14){
            im++;
            imData.postValue(im);
        }
    }

    public void minusHp(){
        if (wetness < MAX_VALUE / 2 || happy < MAX_VALUE / 1.66){
            if (hp > MIN_VALUE){
                hp--;
                hpData.postValue(hp);
            }
        }
    }

    public void addHp(){
        if (wetness > MAX_VALUE / 2 || happy > MAX_VALUE / 1.66){
            if (hp < MAX_VALUE){
                hp++;
                hpData.postValue(hp);
            }
        }
    }

    public void minusHappy(int happy){
        if (this.happy > MIN_VALUE){
            this.happy-= happy;
            happyData.postValue(this.happy);
        }
    }

    public void addHappy(int happy){
        if (this.happy >= MAX_VALUE) {
            return;
        }

        this.happy += happy;

        if(this.happy > MAX_VALUE)
            this.happy = MAX_VALUE;

        happyData.postValue(this.happy);
    }

    public void minusWetness(){
        if (wetness > MIN_VALUE){
            wetness--;
            wetnessData.postValue(wetness);
        }
    }

    public void addWetness(){
        if (wetness < MAX_VALUE){
            wetness++;
            wetnessData.postValue(wetness);
        }
    }

    /*
    // Текущее время
    Date currentDate = new Date();
    // Форматирование времени как "день.месяц.год"
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    String dateText = dateFormat.format(currentDate);

    Date startDate = null;
        try {
        startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateText);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Date endDate = null;
        try {
        endDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateText);
    } catch (ParseException e) {
        e.printStackTrace();
    }


    Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTimeInMillis(startDate.getTime());

    Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeInMillis(endDate.getTime());

    long difference = calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis();
    long days = difference /(24* 60 * 60 * 1000);
    String a = String.valueOf(days);

    TextView imageView = findViewById(R.id.textView);
        imageView.setText(a);*/

}
