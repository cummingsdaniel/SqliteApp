package com.vogella.android.sqliteapp;
import android.content.Context;
import android.net.ConnectivityManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*InternetStuff is a functional based programming class call.
* It does 2 things: returns a yes or no on whether internet is working
* builds the input string if the reply is JSON*/

public class InternetStuff {
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

            InputStream inputStream;
            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getErrorStream();
            } else {
                inputStream = urlConnection.getInputStream();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            reader.close();
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
