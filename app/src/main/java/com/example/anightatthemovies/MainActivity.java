package com.example.anightatthemovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    Spinner ratingSpin;
    EditText etTitle, etGenre, etYear;
    TextView spinStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert =findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinStore = findViewById(R.id.ratingStorage);
        ratingSpin = findViewById(R.id.ratingSpin);


        ratingSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        spinStore.setText("G");
                        break;
                    case 1:
                        spinStore.setText("M18");
                        break;
                    case 2:
                        spinStore.setText("NC16");
                        break;
                    case 3:
                        spinStore.setText("PG");
                        break;
                    case 4:
                        spinStore.setText("PG13");
                        break;
                    case 5:
                        spinStore.setText("R21");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String year = etYear.getText().toString();
                String rating = spinStore.getText().toString();

                DatabaseHelper db = new DatabaseHelper(MainActivity.this);

                // Insert a task
                db.insertTask(title,genre,year,rating);
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(MainActivity.this);
                Intent i = new Intent(MainActivity.this, Showlist.class);
                startActivity(i);
            }
        });
    }
}