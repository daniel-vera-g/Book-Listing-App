package com.example.android.book_listing_app;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by DanielVG on 04.08.17.
 */

/**
 * Loads a list of Books to perform a network request
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    //Tag for the Log Messages
    public static final String LOG_TAG = BookLoader.class.getName();
    //url for the query
    private String mUrl;

    /**
     * Constructos wih:
     * @param context of the activity
     * @param url to load the data from
     */
    public BookLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This method operates on the backround thread
     * */
    @Override
    public List<Book> loadInBackground() {
        //if url is empty retrun early
        if (mUrl == null){
            return null;
        }
        //make the Network request
        //parse the response
        //extract a list of earthquakes
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}