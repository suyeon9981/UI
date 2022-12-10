package com.cookandroid.refrigerator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CoolFragment extends Fragment {

    //context controll
    Context ct;

    ArrayList<Food> foodArrayList = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    //gridview
    public class MyGridAdapter extends BaseAdapter{
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }
        public int getCount(){
            return foodArrayList.toArray().length;
        }
        public Object getItem(int arg0){
            return foodArrayList.get(arg0);
        }
        public long getItemId(int arg0){
            return arg0;
        }
        public View getView(int position, View arg1, ViewGroup arg2){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            arg1 = inflater.inflate(R.layout.griditem, arg2, false);
            ImageView imageview = arg1.findViewById(R.id.gridimage);
            TextView textView = arg1.findViewById(R.id.gridname);
            ImageView imageView2 = arg1.findViewById(R.id.gridlight);
            imageview.setPadding(5,5,5,5);

            //이부분에서 if 처리

            textView.setText(foodArrayList.get(position).getName());

            imageview.setImageResource(foodArrayList.get(position).getImage());
            if(foodArrayList.get(position).getExpiration_dday() <= 0){
                imageView2.setImageResource(R.drawable.gridball);
            }
            else if(foodArrayList.get(position).getExpiration_dday() <= 3){
                imageView2.setImageResource(R.drawable.gridballrd);

            }
            else if(foodArrayList.get(position).getExpiration_dday() <= 5){
                imageView2.setImageResource(R.drawable.gridbally);
            }
            else{
                imageView2.setImageResource(R.drawable.gridballg);
            }
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), DetailFood.class);
                    //imageID[position] -> Foodlist[position].getPicture getName ...
                    intent.putExtra("Image", foodArrayList.get(position).getImage());
                    intent.putExtra("Name", foodArrayList.get(position).getName());
                    intent.putExtra("Date", foodArrayList.get(position).getExpiration_date());
                    intent.putExtra("Storagy", foodArrayList.get(position).getStorage());
                    intent.putExtra("Data", foodArrayList.get(position).getInput_date());
                    intent.putExtra("Point", position);
                    intent.putExtra("Indate", foodArrayList.get(position).getInput_date());
                    intent.putExtra("Cool", foodArrayList.get(position).getCool());
                    startActivityForResult(intent, 0);
                }
            });



            return arg1;
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cool_fragment, container, false);

        //gridview, context
        final GridView gv = (GridView) v.findViewById(R.id.gridViewCool);
        ct = container.getContext();

        Bundle data = getArguments();
        if( data != null ){
            foodArrayList = data.getParcelableArrayList("Food");
        }
        else{
        }


        MyGridAdapter gAdapter = new MyGridAdapter(getActivity());
        gv.setAdapter(gAdapter);


        return v;
    }
}
