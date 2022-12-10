package com.cookandroid.refrigerator;

import android.widget.ListView;

public class ListItem {
    /*
    private int image;
    private String name;

     */
    private RecipyInfo recipyInfo;

    /*
    public int getImage(){
        return image;
    }
    public void setImage(int image){
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

     */

    public RecipyInfo getRecipyInfo(){ return this.recipyInfo;}
    public void setRecipyInfo(RecipyInfo recipyInfo){ this.recipyInfo = recipyInfo ;}

    /*
    ListItem(int image, String name){
        this.image = image;
        this.name = name;
    }

     */
    ListItem(RecipyInfo recipyInfo){
        this.recipyInfo = recipyInfo;
    }
}
