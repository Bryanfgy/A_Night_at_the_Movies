package com.example.anightatthemovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    // Filename of the database
    private static final String DATABASE_NAME = "MoviesList.db";

    private static final String TABLE_MOVIELIST = "Movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Title = "Title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATE = "rate";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIELIST +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_YEAR + " TEXT,"
                + COLUMN_Title + " TEXT, "
                + COLUMN_GENRE + " TEXT, "
                + COLUMN_RATE + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIELIST);
        // Create table(s) again
        onCreate(db);

    }
    public void insertTask(String title,String year,String genre,String ratings){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the title as value
        values.put(COLUMN_Title, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATE, ratings);

        db.insert(TABLE_MOVIELIST, null, values);
        // Close the database connection
        db.close();
    }

    public ArrayList<Movies> getMovies() {
        ArrayList<Movies> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};
        Cursor cursor = db.query(TABLE_MOVIELIST, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                String year = cursor.getString(3);
                String rating= cursor.getString(4);
                Movies obj = new Movies(id, title, genre, year, rating);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public ArrayList<Movies> pgMovies(String pg) {
        ArrayList<Movies> songs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_Title, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};
        String condition = COLUMN_RATE + " Like ?";
        String[] args = {"%" +pg+ "%"};
        Cursor cursor = db.query(TABLE_MOVIELIST, columns, condition, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                String year = cursor.getString(3);
                String rating= cursor.getString(4);
                Movies obj = new Movies(id, title, genre, year, rating);
                songs.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateMovies(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Title, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATE, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_MOVIELIST, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovies(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIELIST, condition, args);
        db.close();
        return result;
    }
}
