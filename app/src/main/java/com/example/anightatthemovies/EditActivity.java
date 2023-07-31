package com.example.anightatthemovies;

import android.content.Intent;
import android.os.Bundle;
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
        }

    }
}
