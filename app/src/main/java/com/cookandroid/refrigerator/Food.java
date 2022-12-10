package com.cookandroid.refrigerator;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Food implements Parcelable {
    String name;                 //이름
    int image;                   //그림
    String input_date;           //date 타입 변경 가능 입고날짜
    int expiration_dday = 0;     // 남은 유통기한
    String expiration_date;      //date 타입 변경 가능 유통기한
    int num;                     //개수
    String storage;              //보관법
    int cool;                   //0 냉장 1 냉동
    public Food(String name){
        this.name = name;
    }
    public Food(String name, String expiration_date, String input_date, int cool, String storage, int image){
        this.name = name;
        this.num = num;
        this.input_date = input_date;
        this.expiration_date = expiration_date;
        this.storage = storage;
        this.cool = cool;
        this.image = image;
        this.expiration_dday = calculateDDay(expiration_date);
    }

    protected Food(Parcel in){
        name = in.readString();
        image = in.readInt();
        input_date = in.readString();
        expiration_date = in.readString();
        num = in.readInt();
        cool = in.readInt();
        storage = in.readString();
        expiration_dday = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getImage(){
        return this.image;
    }
    public String getName(){
        return this.name;
    }
    public String getExpiration_date(){
        return this.expiration_date;
    }
    public String getStorage(){
        return this.storage;
    }
    public int getExpirationDDay(){
        return expiration_dday;
    }

    public int getExpiration_dday() {
        return expiration_dday;
    }

    public String getInput_date() {
        return input_date;
    }

    public int getNum() {
        return num;
    }
    public int getCool(){ return cool;}

    public int calculateDDay(String expiration_date)  {
        long today = System.currentTimeMillis();
        Date date = new Date(today);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Date expi = null;
        try {
            expi = sf.parse(expiration_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long dday = (expi.getTime() - date.getTime())/ (60*60*24*1000);
        return (int) dday;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image);
        parcel.writeString(expiration_date);
        parcel.writeInt(num);
        parcel.writeInt(cool);
        parcel.writeInt(expiration_dday);
        parcel.writeString(storage);
        parcel.writeString(input_date);
    }


    //more code

}
