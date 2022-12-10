package com.cookandroid.refrigerator;

public class Griditem {
    private Food food;

    public Griditem(Food food){
        this.food = food;
    }

    public Food getFood(){ return this.food;}
    public void setFood(Food food){ this.food = food ;}
}
