package com.example.samrat.myweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ForcastAdapter extends ArrayAdapter<Data> {

    public ForcastAdapter(Context context , List<Data> data){

        super(context,0,data);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View listview = convertView;

        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(
                    R.layout.data, parent, false);
        }

        Data data = getItem(position);

        TextView date = (TextView)listview.findViewById(R.id.fdate);
        date.setText(data.getDate());

        TextView day = (TextView)listview.findViewById(R.id.fday);
        day.setText(data.getDay());

        TextView high = (TextView)listview.findViewById(R.id.fhigh);

        String hightemp = data.getHigh();
        int far = Integer.parseInt(hightemp);
        float cel = 5*(far-32)/9;
        high.setText(""+cel+"°C");

        TextView low = (TextView)listview.findViewById(R.id.flow);
        String lowtemp = data.getLow();
        int far1 = Integer.parseInt(lowtemp);
        float cel1 = 5*(far1-32)/9;
        low.setText(""+cel1+"°C");

        TextView text= (TextView)listview.findViewById(R.id.ftext);
        text.setText(data.getText());

        return listview;
    }
}
