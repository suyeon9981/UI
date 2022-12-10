package com.cookandroid.refrigerator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import kotlin.jvm.internal.Intrinsics;

public class Recipy extends Activity {

    Button Recent, Korea, China, West, Japan, Boon, Etc, alert;
    View.OnClickListener clickListener;
    Integer[] btn = { R.id.btnkorea, R.id.btnchina, R.id.btnwest, R.id.btnjapan, R.id.btnboon, R.id.btnetc};

    ListView list;
    ListAdapter adapter;
    ArrayList<RecipyInfo> mainlist = new ArrayList<>();
    ArrayList<RecipyInfo> recipylist = new ArrayList<>();
    ArrayList<RecipyInfo> templist = new ArrayList<>();
    ArrayList<RecipyInfo> sublist = new ArrayList<>();
    ArrayList<RecipyInfo> alelist = new ArrayList<>();

    boolean All = true; //need change    all, can , alert

    //더미
    int image;
    String name;
    String summary;
    String need;
    String recipy;

    int i = 0;

    public void colorInit(){
        West.setBackgroundResource(R.drawable.button);
        China.setBackgroundResource(R.drawable.button);
        Japan.setBackgroundResource(R.drawable.button);
        Boon.setBackgroundResource(R.drawable.button);
        Etc.setBackgroundResource(R.drawable.button);
        Korea.setBackgroundResource(R.drawable.button);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipy);

        Recent = (Button)findViewById(R.id.btnrecent);
        Korea = (Button)findViewById(R.id.btnkorea);
        China = (Button)findViewById(R.id.btnchina);
        West = (Button)findViewById(R.id.btnwest);
        Japan = (Button)findViewById(R.id.btnjapan);
        Boon = (Button)findViewById(R.id.btnboon);
        Etc = (Button)findViewById(R.id.btnetc);
        list = (ListView)findViewById(R.id.listview);
        alert = (Button)findViewById(R.id.btnalert);

        Recent.setText("만들 수 있는 레시피");



        //동적 추가 위한 리스트 -> 메인 리스트와 타입 맞춘 후  메인 리스트에 추가
        /*
        final ArrayList<String> midlist = new ArrayList<String>();
        final String[] item = {" item"," item"," item"," item"," item"," item"," item"," item"," item"," item"," item"," item"," item"," item"," item",};
         */
        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();

        mainlist = (ArrayList<RecipyInfo>) rxIntent.getSerializableExtra("Main");
        recipylist = (ArrayList<RecipyInfo>) rxIntent.getSerializableExtra("Object");
        alelist = (ArrayList<RecipyInfo>) rxIntent.getSerializableExtra("Alert");

        templist = (ArrayList<RecipyInfo>) recipylist.clone();
        sublist = (ArrayList<RecipyInfo>) templist.clone();
        adapter = new ListAdapter();
        //adapter 에 리스트 추가 <-밑의 버튼 리스너에 각각 지정 (어떤 객체를 지정할지는 미리 설정)
       /*
        adapter.addItem(new ListItem(R.drawable.apple, "음식이름"));
        adapter.addItem(new ListItem(R.drawable.apple, "음식이름"));
        adapter.addItem(new ListItem(R.drawable.apple, "음식이름"));
        adapter.addItem(new ListItem(R.drawable.apple, "음식이름"));


        */
        int size = recipylist.size();
        for(int i = 0; i < size; i++){
            adapter.addItem(new ListItem(recipylist.get(i)));
        }

        list.setAdapter(adapter);

        /*
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                int size = alelist.size();

                for(int i = 0; i < size; i++){
                    adapter.addItem(new ListItem(alelist.get(i)));
                }

                list.setAdapter(adapter);

            }
        });


         */
        Recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(All){
                    All = false;
                    Recent.setText("전체 레시피");
                    recipylist.clear();
                    adapter.clear();
                    recipylist =(ArrayList<RecipyInfo>) mainlist.clone();
                    int size = recipylist.size();
                    for(int i = 0; i < size; i++){
                        adapter.addItem(new ListItem(recipylist.get(i)));
                    }

                    list.setAdapter(adapter);

                }
                else{
                    All = true;
                    Recent.setText("만들 수 있는 레시피");
                    recipylist.clear();
                    adapter.clear();
                    recipylist =(ArrayList<RecipyInfo>) templist.clone();
                    int size = recipylist.size();
                    for(int i = 0; i < size; i++){
                        adapter.addItem(new ListItem(recipylist.get(i)));
                    }

                    list.setAdapter(adapter);
                }
                colorInit();
            }
        });

        /*
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, item);
        list.setAdapter(adapter);
         */
        //리스트 동적 추가
        /*midlist.add(type);
          adapter.notifyDataSetChanged(); //반영
         */
        //리스트 동적 삭제
        /*
        클릭 리스너로 position(int) 값 가져옴
        midlist.remove(position);
        adapter.notifyDataSetChanged();
         */

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;
                Intent intent = new Intent(getApplicationContext(), DetailRecipy.class);
                intent.putExtra("Object", recipylist.get(i));

                startActivity(intent);
            }
        });





        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnkorea:
                        //code
                        colorInit();
                        Korea.setBackgroundResource(R.drawable.button_push);

                        templist = (ArrayList<RecipyInfo>) sublist.clone();
                        recipylist = (ArrayList<RecipyInfo>) templist.clone();
                        recipylist.clear();
                        adapter.clear();
                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }

                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "한식").clone();
                        int size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);




                        //list 변경 (flagment?)
                        break;
                    case R.id.btnchina:
                        //code
                        colorInit();

                        China.setBackgroundResource(R.drawable.button_push);

                        templist = (ArrayList<RecipyInfo>) sublist.clone();
                        recipylist = (ArrayList<RecipyInfo>) templist.clone();
                        recipylist.clear();
                        adapter.clear();
                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }

                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "중식").clone();
                        size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);


                        break;
                    case R.id.btnwest:
                        //code
                        colorInit();
                        West.setBackgroundResource(R.drawable.button_push);

                        ListAdapter adapter2 = new ListAdapter();
                        templist = (ArrayList<RecipyInfo>) sublist.clone();
                        recipylist = (ArrayList<RecipyInfo>) templist.clone();
                        recipylist.clear();
                        adapter.clear();
                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }

                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "양식").clone();
                        size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);



                        break;
                    case R.id.btnjapan:
                        //code
                        colorInit();
                        Japan.setBackgroundResource(R.drawable.button_push);

                        ListAdapter adapter3 = new ListAdapter();
                        templist = (ArrayList<RecipyInfo>) sublist.clone();
                        recipylist = (ArrayList<RecipyInfo>) templist.clone();
                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }
                        recipylist.clear();
                        adapter.clear();
                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "일식").clone();
                        size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);



                        break;
                    case R.id.btnboon:
                        //code
                        colorInit();
                        Boon.setBackgroundResource(R.drawable.button_push);


                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }
                        recipylist.clear();
                        adapter.clear();
                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "분식").clone();
                        size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);


                        break;
                    case R.id.btnetc:
                        //code
                        colorInit();
                        Etc.setBackgroundResource(R.drawable.button_push);

                        templist = (ArrayList<RecipyInfo>) sublist.clone();
                        recipylist = (ArrayList<RecipyInfo>) templist.clone();
                        recipylist.clear();
                        adapter.clear();
                        if(!All){
                            templist.clear();
                            templist = (ArrayList<RecipyInfo>) mainlist.clone();
                        }
                        recipylist = (ArrayList<RecipyInfo>)findCategory(templist, "기타").clone();
                        size = recipylist.size();

                        for(int i = 0; i < size; i++){
                            adapter.addItem(new ListItem(recipylist.get(i)));
                        }

                        list.setAdapter(adapter);


                        break;
                }
            }
        };

        Korea.setOnClickListener(clickListener);
        West.setOnClickListener(clickListener);
        China.setOnClickListener(clickListener);
        Japan.setOnClickListener(clickListener);
        Boon.setOnClickListener(clickListener);
        Etc.setOnClickListener(clickListener);


        //list 전체 레시피 생성 후 종류따라 setvisible로

        //list btn 넣어야 할 기능
        /*
        btnDetail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailRecipy.class);
                intent.putExtra("Name", name);
                intent.putExtra("Summary", summary);
                intent.putExtra("Need", need);
                intent.putExtra("Recipy", recipy);
                startActivity(intent);
            }

        });
         */
    }


    //
    public static final ArrayList<RecipyInfo> findCategory(@NotNull ArrayList<RecipyInfo> recipeList, @NotNull String category) {
        Intrinsics.checkNotNullParameter(recipeList, "recipeList");
        Intrinsics.checkNotNullParameter(category, "category");
        ArrayList<RecipyInfo> categoryList = new ArrayList<>();
        int categoryIndex = 0;
        int index = 0;

        for(int var5 = recipeList.size(); index < var5; ++index) {
            if (Intrinsics.areEqual(recipeList.get(index).getCategory(), category)) {
                categoryList.add(recipeList.get(index));
                ++categoryIndex;
            }
        }

        return categoryList;
    }
}
