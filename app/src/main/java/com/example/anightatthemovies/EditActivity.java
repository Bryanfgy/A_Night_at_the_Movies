package com.example.anightatthemovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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

                if(editSpin.getSelectedItemPosition() == 0){
                    spinStore.setText("G");
                } else if (editSpin.getSelectedItemPosition() == 1) {
                    spinStore.setText("M18");
                } else if (editSpin.getSelectedItemPosition() == 2) {
                    spinStore.setText("NC16");
                } else if (editSpin.getSelectedItemPosition() == 3) {
                    spinStore.setText("PG");
                } else if (editSpin.getSelectedItemPosition() == 4) {
                    spinStore.setText("PG13");
                } else if (editSpin.getSelectedItemPosition() == 5) {
                    spinStore.setText("R21");
                }
                Log.i("rating",spinStore.getText().toString());
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
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Delete Entry?");
                myBuilder.setMessage("Are you sure you want to delete this entry? This cannot be undone.");
                myBuilder.setCancelable(false);

                //1st option(changes tvdemo2's contents)
                myBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbh = new DatabaseHelper(EditActivity.this);
                        dbh.deleteMovies(data.getId());
                        finish();
                    }
                });


                //2nd option
                myBuilder.setNeutralButton("Cancel",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });




        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Return");
                myBuilder.setMessage("Are you sure you want to return without saving this entry?");
                myBuilder.setCancelable(false);

                //1st option(changes tvdemo2's contents)
                myBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(EditActivity.this, Showlist.class);
                        startActivity(i);
                    }
                });


                //2nd option
                myBuilder.setNeutralButton("Cancel",null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
    }
}
