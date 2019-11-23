package com.vogella.android.sqliteapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SHAJIB-PC on 9/9/2019.
 */

class ListNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
//    public Object getItem(int position) {
////        return position;
////    }
    public HashMap<String, String>  getItem(int position) {
        return data.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public String getKey(int position) {
        return "";
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.list_row, parent, false);
            holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.sdetails = (TextView) convertView.findViewById(R.id.details);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.saveToFavs = (Button) convertView.findViewById(R.id.news_save_to_fav);
            holder.goToPage = (Button) convertView.findViewById(R.id.news_goto_article);
            convertView.setTag(holder);
//        } else {
//            holder = (ListNewsViewHolder) convertView.getTag();
//        }
        holder.galleryImage.setId(position);
        holder.author.setId(position);
        holder.title.setId(position);
        holder.sdetails.setId(position);
        holder.time.setId(position);
        holder.saveToFavs.setOnClickListener(fbtn -> {
            //saveDataToBD.insert(getItem(position));
            DatabaseHelper dbhelper = new DatabaseHelper(activity);
            HashMap<String, String> map = getItem(position);

            if(dbhelper.insertData(map.get(NewsMain.KEY_TITLE),
                        map.get(NewsMain.KEY_URL),
                        map.get(NewsMain.KEY_DESCRIPTION),
                    map.get(NewsMain.KEY_URLTOIMAGE))){
                Toast.makeText(activity, "saved to favourites", Toast.LENGTH_SHORT);
            }else{
                Toast.makeText(activity, "Nope", Toast.LENGTH_SHORT);

            }

        });

        HashMap<String, String> song = data.get(position);
        String url=song.get(NewsMain.KEY_URL);
        holder.goToPage.setOnClickListener(pbtn ->{
            Intent i = new Intent(activity, Article.class);
            i.putExtra("url",url );
            activity.startActivity(i);
        });
        try{
            holder.author.setText(song.get(NewsMain.KEY_AUTHER));
            holder.title.setText(song.get(NewsMain.KEY_TITLE));
            holder.time.setText(song.get(NewsMain.KEY_PUBLISHDATE));
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
        return convertView;
    }



//    private void saveDataToBD(Object item) {
//
//    }
    }

class ListNewsViewHolder {
    ImageView galleryImage;
    TextView author, title, sdetails, time;
    Button saveToFavs, goToPage;
}
