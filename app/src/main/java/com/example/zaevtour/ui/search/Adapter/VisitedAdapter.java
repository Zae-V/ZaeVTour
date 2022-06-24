package com.example.zaevtour.ui.search.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zaevtour.R;

public class VisitedAdapter extends BaseAdapter {

    Context context;
    String[] cityName;

    LayoutInflater inflater;

    public VisitedAdapter(Context context, String[] cityName) {
        this.context = context;
        this.cityName = cityName;
    }

    @Override
    public int getCount() {
        return cityName.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = inflater.inflate(R.layout.visited_city,null);
        }
        TextView textView = view.findViewById(R.id.cityName);
        textView.setText(cityName[i]);

        return view;
    }
}
