package com.cookandroid.refrigerator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context context;


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View ConvertView, ViewGroup viewGroup) {
        context = viewGroup.getContext();
        ListItem listItem = items.get(position);

        if(ConvertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ConvertView = inflater.inflate(R.layout.list_recipy, viewGroup, false);

        }

        ImageView imageView = ConvertView.findViewById(R.id.list_item_image);
        TextView textView = ConvertView.findViewById(R.id.list_item_name);

        imageView.setImageResource(listItem.getRecipyInfo().picture);
        textView.setText(listItem.getRecipyInfo().name);

        return ConvertView;
    }
    public void addItem(ListItem item){
        items.add(item);
    }
    //초기화
    public void clear(){ items.clear();}
}
