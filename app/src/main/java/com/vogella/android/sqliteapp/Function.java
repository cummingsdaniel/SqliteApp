package com.vogella.android.sqliteapp;
import android.content.Context;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Function {
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager)context.getSystemService
                (context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public static String excuteGet(String targetURL) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(targetURL);
            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestProperty("Content-type", "application/json; charset=utf-8");
//            urlConnection.setRequestProperty("Content-Language", "en-US");
//            urlConnection.setUseCaches(false);
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(false);

            InputStream is;
            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                is = urlConnection.getErrorStream();
            } else {
                is = urlConnection.getInputStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        }
        catch (Exception e) {
            return null;
        }
        finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
