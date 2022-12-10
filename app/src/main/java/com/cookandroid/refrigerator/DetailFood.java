package com.cookandroid.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class DetailFood extends Activity {

    Button foodsub;
    TextView food_name, food_date, food_storagy, food_indate;
    ImageView food_image;
    String name;
    String date;
    String storagy;
    String indate;
    int image;
    int point;
    int cool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailfood);

        foodsub = (Button)findViewById(R.id.btnfoodsub);
        food_image = (ImageView)findViewById(R.id.food_image);
        food_name = (TextView)findViewById(R.id.food_name);
        food_date = (TextView)findViewById(R.id.food_date);
        food_storagy = (TextView)findViewById(R.id.food_storagy);
        food_indate = (TextView)findViewById(R.id.food_indate);
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        image = extras.getInt("Image");
        name = extras.getString("Name");
        date = "유통기한"+"\n"+ extras.getString("Date");
        indate = "입고날짜"+"\n"+extras.getString("Indate");
        storagy = "<보관방법>\n\n" + extras.getString("Storagy");

        //point 값에 위치 저장 ->다시 main activity 에 전달하여 리스트 수정
        point = extras.getInt("Point");
        cool = extras.getInt("Cool");

        food_image.setImageResource(image);
        food_name.setText(name);
        food_date.setText(date);
        food_indate.setText(indate);
        food_storagy.setText(storagy);

        foodsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent outintent = new Intent(getApplicationContext(), MainActivity.class);
               outintent.putExtra("Point", point);
               if(cool == 0){
                   setResult(4, outintent);
               }
               else if(cool == 1){
                   setResult(5, outintent);
               }
               finish();
            }
        });
        //데이터 입력 못받으면 avd오류
        /*
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        name = extras.getString("Name");
        date = extras.getString("Date");
        storagy = extras.getString("Storagy");
        image = R.drawable.apple;

        food_name.setText(name);
        food_date.setText(date);
        food_storagy.setText(storagy);
        food_image.setImageResource(image);
        */
    }

}
