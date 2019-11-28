package com.vogella.android.sqliteapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


class ListNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private boolean isFav;

    public ListNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d,boolean isFav) {
        activity = a;
        data=d;
        this.isFav = isFav;
    }
    public int getCount() {

        return data.size();
    }

    public HashMap<String, String>  getItem(int position) {
        return data.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ListNewsViewHolder holder = null;
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.newsapp_list_row, parent, false);
            holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.sdetails = (TextView) convertView.findViewById(R.id.details);
            holder.saveToFavs = (Button) convertView.findViewById(R.id.news_save_to_fav);
            holder.goToPage = (Button) convertView.findViewById(R.id.news_goto_article);
            holder.removeButton = (Button) convertView.findViewById(R.id.news_remove_from_favs);
            convertView.setTag(holder);

        final View view=convertView;
        holder.galleryImage.setId(position);
        holder.title.setId(position);
        holder.sdetails.setId(position);
        DatabaseHelper dbhelper = new DatabaseHelper(activity);

        holder.saveToFavs.setOnClickListener(fbtn -> {

            Log.d("fddasgasggfg", "Saved to fav");
            HashMap<String, String> map = getItem(position);

            if(dbhelper.insertData(map.get(NewsMain.KEY_TITLE),
                        map.get(NewsMain.KEY_URL),
                        map.get(NewsMain.KEY_DESCRIPTION),
                    map.get(NewsMain.KEY_URLTOIMAGE))){
                Log.d("fddasgasggfg", "Saved");
                Snackbar.make(view,"ascadad",Snackbar.LENGTH_SHORT);

                Toast.makeText(activity, "saved to favourites", Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(activity, "Nope", Toast.LENGTH_SHORT);

            }

            new AlertDialog.Builder(activity)
                    .setMessage("This article was saved to favourites")
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Whatever...
                        }
                    }).show();

        });

        HashMap<String, String> song = data.get(position);
        String url=song.get(NewsMain.KEY_URL);
        holder.removeButton.setOnClickListener(rbtn ->{
            if(dbhelper.removeData(url)) {
                Log.d("it is deleted", "deleted");
                activity.finish();
            }
        });
        holder.goToPage.setOnClickListener(pbtn ->{
            Intent i = new Intent(activity, Article.class);
            i.putExtra("url",url );
            activity.startActivity(i);
        });
        try{
            holder.title.setText(song.get(NewsMain.KEY_TITLE));
            holder.sdetails.setText(song.get(NewsMain.KEY_DESCRIPTION));

            if(song.get(NewsMain.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.get()
                        .load(song.get(NewsMain.KEY_URLTOIMAGE))
                        .resize(300, 200)
                        .centerCrop()
                        .into(holder.galleryImage);
            }
        }catch(Exception e) {}

        if(isFav) {
            holder.removeButton.setVisibility(View.VISIBLE);
            holder.saveToFavs.setVisibility(View.INVISIBLE);
        }else{
            holder.removeButton.setVisibility(View.INVISIBLE);
            holder.saveToFavs.setVisibility(View.VISIBLE);
        }

        return convertView;
    }


    }

class ListNewsViewHolder {
    ImageView galleryImage;
    TextView /*author,*/ title, sdetails /*,time*/;
    Button saveToFavs, goToPage, removeButton;
}
