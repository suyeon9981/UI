package com.cookandroid.refrigerator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Setting extends Fragment {

    Button alart, day1, day3, day5, day7, btnOn, btnOff, btnHelp;

    /*
    FragmentSettingListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FoodPlus.FragmentPlusListener){
            listener = (FragmentSettingListener) context;
        }
        else{
            //오류
        }
    }


     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting, container, false);

        alart = v.findViewById(R.id.alertname);
        day1 = v.findViewById(R.id.btn1day);
        day3 = v.findViewById(R.id.btn3day);
        day5 = v.findViewById(R.id.btn5day);
        day7 = v.findViewById(R.id.btn7day);

        btnOn = v.findViewById(R.id.btnon);
        btnOff = v.findViewById(R.id.btnoff);

        btnHelp = v.findViewById(R.id.btnhelp);

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    /*
    public interface FragmentSettingListener{
        void onInputSettingSend(String input);
    }

     */
}
