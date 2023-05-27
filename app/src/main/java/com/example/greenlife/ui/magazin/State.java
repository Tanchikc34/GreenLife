package com.example.greenlife.ui.magazin;

public class State {

    private String name; // название
    private int flagResource; // ресурс

    public State(String name, int flag){

        this.name=name;
        this.flagResource=flag;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }
}