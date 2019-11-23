package com.vogella.android.sqliteapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    ListView listFavs;
    ProgressBar loader;
    Button hateNow;
    ListNewsAdapter listFavsAdapter = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.favourites_list);
//        listFavs = findViewById(R.id.listFav);
//        loader = findViewById(R.id.loader);
//        listFavs.setEmptyView(loader);
//        listFavs.setAdapter(listFavsAdapter = new ListNewsAdapter());
//
//        DatabaseHelper dbOpener = new DatabaseHelper(this);
//        SQLiteDatabase db = dbOpener.getWritableDatabase();
//        listFavsAdapter


    }
}
