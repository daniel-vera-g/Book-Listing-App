package com.example.android.book_listing_app;

/**
 * Created by DanielVG on 02.08.17.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.author;

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

    /**
     * convert the input stream into a string
     * and return the JSON response from the server
     * */
    private static String readFromInputStream(InputStream inputStream) throws IOException{
        //create new StringBuilder Object
        StringBuilder output = new StringBuilder();

        //if the inputStream is positive build up the Json String
        if (inputStream != null){
            //create InputStream reader Object
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            //create a new Buffered reader Object to read the Input stream
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //declare new String variable and assign the value of the read line from the buffered reader
            String line = bufferedReader.readLine();
            //loop to build the String with the help of the string builder and keep reading new lines from the input stream as long as line is not null
            while (line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        //return the StringBuilder Object and convert it to string
        return output.toString();
    }

    /**
     * Method to extraxt the important features from the given Json response
     * */
    private static List<Book> extractFeaturesfromJson(String jsonBookResponse){
        //return early if the String response is empty
        if (TextUtils.isEmpty(jsonBookResponse)){
            return null;
        }

        //create an empty list to add the Book informations to
        List<Book> books = new ArrayList<>();

        //parse the json response and extract the important features
        //if there is a problem fire up the exception
        try {
            //create new book Objects to store the data to

            //create Json Object with the Json response in it
            JSONObject bookRootInfo = new JSONObject(jsonBookResponse);
            //extract the  items Array from the Root to looop through it and get the information
            JSONArray items = bookRootInfo.getJSONArray("items");
            //loop through the Json array of items to get the important imformation about the book
            for (int i = 0; i < items.length(); i++){
                //create an Book object for the item at the position i
                JSONObject Book = items.getJSONObject(i);
                //extract the volume Info Object from the Book Object
                JSONObject VolumeInfo = Book.getJSONObject("volumeInfo");


                //extract the title of the book
                String title = VolumeInfo.getString("title");
                //extract the subtitle
                String subtitle = VolumeInfo.getString("subtitle");

                //extract the Array with the Name of the Author or authors
                JSONArray authors = VolumeInfo.getJSONArray("authors");
                //loop through the array of authors and get all the authors
                //Define aa Array of Strings to store the JSON Array values
                ArrayList<String> listOfAuthors = new ArrayList<String>();
                //Loop through the JSON Array and assign each JSON Array value to the Array of Strings
                for (int j = 0; j < authors.length(); j++){
                    //assign the value of the JSON Array to the Array of strings
                    listOfAuthors.add(authors.getString(j));
                }
                //save list of auhtors in from ArrayList into single Strings to give it as parameter in the Book Object Invocation
                String author = TextUtils.join(", ", listOfAuthors);


                //extract the published Date
                String publishedDate = VolumeInfo.getString("publishedDate");
                //extract the number of pages
                int numberPages = VolumeInfo.getInt("pageCount");
                //etract the link to preview the book
                String previewLink = VolumeInfo.getString("previewLink");

                //Create a new Book Object to store the values in
                Book newBook = new Book(title, subtitle, author, publishedDate, numberPages, previewLink);
                books.add(newBook);
            }

        }catch (JSONException e){
            //if there is some erro while executing the try block throw the exception
            //this helps the app not to crash and prints a log message
            Log.e(LOG_TAG, "Problem with parsing the Book JSON response", e);
        }
        //return a list of books
        return books;
    }
}
