package com.vogella.android.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FavouritesActivity extends AppCompatActivity {
    ListView listFavs;
    ProgressBar loader;
    Button hateNow;
    ListNewsAdapter listFavsAdapter = null;
    ArrayList<HashMap<String, String>> list =null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_list);
        list=new ArrayList<>();
        listFavs = findViewById(R.id.listFav);
        loader = findViewById(R.id.loader);
        listFavs.setEmptyView(loader);
        listFavs.setAdapter(listFavsAdapter = new ListNewsAdapter(this, list, true));


        DatabaseHelper dbOpener = new DatabaseHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
        String[] columns = {DatabaseHelper.COL_URL, DatabaseHelper.COL_TITLE, DatabaseHelper.COL_DESCRIPTION, DatabaseHelper.COL_IMAGE_URL};
        Cursor dbresult = db.query(false, DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);

        int urlColIndex = dbresult.getColumnIndex(DatabaseHelper.COL_URL);
        int titleColIndex = dbresult.getColumnIndex(DatabaseHelper.COL_TITLE);
        int descriptionColIndex = dbresult.getColumnIndex(DatabaseHelper.COL_DESCRIPTION);
        int imageColIndex = dbresult.getColumnIndex(DatabaseHelper.COL_IMAGE_URL);

        while(dbresult.moveToNext())
        {
            String url = dbresult.getString(urlColIndex);
            String title = dbresult.getString(titleColIndex);
            String description = dbresult.getString(descriptionColIndex);
            String imageUrl = dbresult.getString(imageColIndex);
            HashMap<String, String> data = new HashMap<>();
            //add the new Contact to the array list:
            data.put(NewsMain.KEY_URL, url);
            data.put(NewsMain.KEY_TITLE, title);
            data.put(NewsMain.KEY_DESCRIPTION, description);
            data.put(NewsMain.KEY_URLTOIMAGE, imageUrl);
            list.add(data);
        }
        listFavsAdapter.notifyDataSetChanged();


    }
}
