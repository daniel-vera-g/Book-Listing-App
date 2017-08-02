package com.example.android.book_listing_app;

/**
 * Created by DanielVG on 02.08.17.
 */

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** Helper methods to receive the information requested from the Google Books API*/
public class QueryUtils {

    /** Tag for the log messages*/
    public static final String LOG_TAG = QueryUtils.class.getName();

    /** Empty constructor because Class should only hold methods and and work with the API*/
    public QueryUtils(){

    }

    /** method to return a URL Object from the given url*/
    public static URL createURL(String stringUrl){
        //declare URL object
        URL url = null;
        //create URL Object
        try {
            url = new URL(stringUrl);
        }catch (MalformedURLException e){
            //Log tag for the Exception
            Log.e(LOG_TAG, "Error wit creating the URL: ", e);
        }
        //returning the new URL object
        return url;
    }

    /** make a http response to the server and return a string response*/
    private static String makeHttpRequest(URL url) throws IOException{
        //declare JSON response
        String jsonResponse = "";

        //if url is null return early and don't make request
        if (url == null){
            return jsonResponse;
        }

        //create httpConnection and InputStream Object
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //set up the connection and make request
        try {
            //open url connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //set timeout times
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            //Connect with remote server
            urlConnection.connect();

            //check if url Connection was successfull
            if (urlConnection.getResponseCode() == 200){
                //assign value of InputStream to input stream variable
                inputStream = urlConnection.getInputStream();
                //read the input stream with a helper method and assign value to the json response
                jsonResponse = readFromInputStream(inputStream);
            }else {
                //log message if url connection was not successful
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }catch (IOException e){
            //log tag for the IOException
            Log.e(LOG_TAG, "Problem receiving the google books Json response", e);
        }finally {
            //if request has ended disconnect and close the input stream
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
            //return the json response
            return jsonResponse;
        }


    }
}
