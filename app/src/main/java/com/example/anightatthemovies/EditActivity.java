package com.example.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText editTitle, editGenre, editYear;
    Spinner editSpin;
    Button btnUpdate, btnDelete, Return;
    TextView spinStore;
    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTitle = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editYear = findViewById(R.id.editYear);

        editSpin = findViewById(R.id.editSpin);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        Return = findViewById(R.id.Return);

        spinStore = findViewById(R.id.ratingStorage);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        editTitle.setText(data.getTitle());
        editGenre.setText(data.getGenre());
        editYear.setText(data.getYear());
        if(data.getRating().equals("G")){
            editSpin.setSelection(0);
        } else if (data.getRating().equals("M18")) {
            editSpin.setSelection(1);
        } else if (data.getRating().equals("NC16")) {
            editSpin.setSelection(2);
        } else if (data.getRating().equals("PG")) {
            editSpin.setSelection(3);
        } else if (data.getRating().equals("PG13")) {
            editSpin.setSelection(4);
        } else if (data.getRating().equals("R21")) {
            editSpin.setSelection(5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "";
                if(data.getRating().equals("G")){
                    spinStore.setText("G");
                } else if (data.getRating().equals("M18")) {
                    spinStore.setText("M18");
                } else if (data.getRating().equals("NC16")) {
                    spinStore.setText("NC16");
                } else if (data.getRating().equals("PG")) {
                    spinStore.setText("PG");
                } else if (data.getRating().equals("PG13")) {
                    spinStore.setText("PG13");
                } else if (data.getRating().equals("R21")) {
                    spinStore.setText("R21");
                }
                DatabaseHelper dbh = new DatabaseHelper(EditActivity.this);
                data.setTitle(editTitle.getText().toString());
                data.setYear(editYear.getText().toString());
                data.setGenre(editGenre.getText().toString());
                data.setRating(spinStore.getText().toString());
                dbh.updateMovies(data);
                dbh.close();
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbh = new DatabaseHelper(EditActivity.this);
                dbh.deleteMovies(data.getId());
                finish();
            }
        });

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditActivity.this, Showlist.class);
                startActivity(i);
            }
        });
    }
}
