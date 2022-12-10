package com.cookandroid.refrigerator;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import kotlin.jvm.internal.Intrinsics;

public class RecipyInfo implements Serializable {
    String name;            //이름
    int picture;              //레시피 사진
    String desc;            //설명
    String category;        //타입 한식,중식 등
    String youtubeLink;     //유튜브 url
    int sumnail;            //섬네일 사진
    ArrayList<String> essentialIngredients;     //필수
    ArrayList<String> additionalIngredients;    //부가


    RecipyInfo(String name) {
        this.name = name;
    }
    public RecipyInfo(String name, String desc, String youtubeLink, String category, ArrayList<String> essentialIngredients, ArrayList<String> additionalIngredients, int picture, int sumnail) {
        this.name = name;
        this.desc = desc;
        this.youtubeLink = youtubeLink;
        this.category = category;
        this.essentialIngredients = essentialIngredients;
        this.additionalIngredients = additionalIngredients;
        this.picture = picture;
        this.sumnail = sumnail;

    }

    RecipyInfo(String name, String desc, String youtubeLink, String category, ArrayList<String> essentialIngredients, ArrayList<String> additionalIngredients) {
        this.name = name;
        this.desc = desc;
        this.youtubeLink = youtubeLink;
        this.category = category;
        this.essentialIngredients = essentialIngredients;
        this.additionalIngredients = additionalIngredients;
    }
    public String getName(){
        return this.name;
    }
    public String getDesc(){
        return this.desc;
    }
    public String getYoutubeLink(){
        return this.youtubeLink;
    }
    public String getCategory(){
        return this.category;
    }
    public int getPicture(){
        return this.picture;
    }
    public int getSumnail(){
        return this.sumnail;
    }
    public ArrayList<String> getEssentialIngredients(){return  this.essentialIngredients;}

    public ArrayList<String> getAdditionalIngredients() {
        return additionalIngredients;
    }
}




