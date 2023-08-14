package com.example.anightatthemovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_contact;
    int layout_id;
    ArrayList<Movies> contactList;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects){
        super(context, resource, objects);
        parent_contact = context;
        layout_id = resource;
        contactList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) parent_contact
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvName = rowView.findViewById(R.id.tvTitle);

        TextView tvCode = rowView.findViewById(R.id.tvGenre);
        ImageView ivGender = rowView.findViewById(R.id.imageRating);
        TextView tvYear = rowView.findViewById(R.id.tvYear);

        Movies currentItem = contactList.get(position);
        tvName.setText(currentItem.getTitle());
        tvCode.setText( currentItem.getGenre());
        tvYear.setText(currentItem.getYear() + "");
        if (currentItem.getRating().equals("G")) {
            ivGender.setImageResource(R.drawable.rating_g);
        } else if(currentItem.getRating().equals("M18")){
            ivGender.setImageResource(R.drawable.rating_m18);
        } else if(currentItem.getRating().equals("NC16")){
            ivGender.setImageResource(R.drawable.rating_nc16);
        } else if(currentItem.getRating().equals("PG")){
            ivGender.setImageResource(R.drawable.rating_pg);
        } else if(currentItem.getRating().equals("PG13")){
            ivGender.setImageResource(R.drawable.rating_pg13);
        } else if(currentItem.getRating().equals("R21")){
            ivGender.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }

}
