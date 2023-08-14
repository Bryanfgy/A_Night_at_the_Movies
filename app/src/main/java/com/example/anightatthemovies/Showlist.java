package com.example.anightatthemovies;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Showlist extends AppCompatActivity {
    Button btnPg13 ,btnRtn;
    ListView lv;
    ArrayList<Movies> al;
    CustomAdapter aa;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showlist);

        DatabaseHelper db = new DatabaseHelper(Showlist.this);
        btnPg13 = findViewById(R.id.btnPg13);
        btnRtn = findViewById(R.id.btnreturn);
        lv = findViewById(R.id.MovieListview);
        al = new ArrayList<>();
        aa = new CustomAdapter(this, R.layout.customlist, al);
        lv.setAdapter(aa);
        DatabaseHelper dbh = new DatabaseHelper(Showlist.this);
        al.clear();
        al.addAll(dbh.getMovies());
        aa.notifyDataSetChanged();
        dbh.close();

        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies data = al.get(position);
                Intent i = new Intent(Showlist.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnPg13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(Showlist.this);
                al.clear();
                al.addAll(dbh.pgMovies("PG13"));
                aa.notifyDataSetChanged();
            }
        });

        btnRtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Showlist.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DatabaseHelper dbh = new DatabaseHelper(Showlist.this);
        al.clear();
        al.addAll(dbh.getMovies());
        aa.notifyDataSetChanged();
        dbh.close();
    }
}
