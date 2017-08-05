package com.example.android.book_listing_app;


import android.app.LoaderManager;
import android.app.usage.UsageEvents;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{
    /**
     * Variables
     * */
    //log tag for the logs
    private static final String LOG_TAG = MainActivity.class.getName();
    //static int to uniquely verify the loader
    public static final int OPERATION_SEARCH_LOADER = 1;
    //url to make the request to
    public static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist_activity);

        //make reference of the connectivity manager so I can check the netwrok status
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //get details about the current network status
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        //check for connection and then fetch data
        if (networkInfo != null && networkInfo.isConnected()){
            //get a reference of the loader manager
            Log.v(LOG_TAG, "LOG: The loader manager will start");
            LoaderManager loaderManager = getLoaderManager();

            //Initialize the loader
            loaderManager.initLoader(OPERATION_SEARCH_LOADER, null, this);
        }else {
            //if there is no internet connection avalaible display en erro
            //TODO: Implement a loading indicatorn and set an empty textView to get the user know that there is no internet connection
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        //create a new loader for the given url
        return new BookLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        //TODO: Make a loading indicator until data has loaden

        //TODO: Make empty textview when there are no Earthwuakes

        //TODO: Clear the adapter

        //TODO: If there is a valid List of Books add them to the adapter and update the listview
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        //Reset Loader so that we can clear out existing data
    }
}
