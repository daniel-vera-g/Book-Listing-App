package com.example.android.book_listing_app;


import android.app.LoaderManager;
import android.app.usage.UsageEvents;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{
    /**
     * Variables
     * */
    //url to make the request to
    public static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=5";
    //static int to uniquely verify the loader
    public static final int OPERATION_SEARCH_LOADER = 1;
    //Adapter for the list of Books
    public BookAdapter mAdapter;
    //log tag for the logs
    private static final String LOG_TAG = MainActivity.class.getName();
    //empty Textview to be displayed when there is no Internet connection
    public TextView emptyTextView;

    /**
     * onCreate Method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist_activity);

        //find a reference to the Listview in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        //define the emptytextView & set ot to the Listview
        emptyTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyTextView);

        //create new Adapter that takes an empty list of Books as Input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        //set the adapter to the Listview
        //This leads to the population of the user Interface
        bookListView.setAdapter(mAdapter);

        //TODO: Implement the On Click listener for the Search button as well as the onlick listener for the preview link button


        //make reference of the connectivity manager so I can check the netwrok status
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //get details about the current network status
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        //check for connection and then fetch data
        if (networkInfo != null && networkInfo.isConnected()){
            //get a reference of the loader manager
            LoaderManager loaderManager = getLoaderManager();

            //Initialize the loader
            loaderManager.initLoader(OPERATION_SEARCH_LOADER, null, this);
        }else {
            //if there is no internet connection avalaible display en erro
            //TODO: Implement a loading indicatorn and set an empty textView to get the user know that there is no internet connection

            //update empty state with no connection erro message
            Log.v(LOG_TAG, "There is no internet connection");
            emptyTextView.setText(R.string.noConnection);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        //create a new loader for the given url
        return new BookLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        //TODO: Make a loading indicator until data has loaden

        //empty textview when there are no Books
        Log.v(LOG_TAG, "Empty Text View will be set");
        emptyTextView.setText(R.string.noConnection);
        Log.v(LOG_TAG, "The empty textview was not set");
        //Clear the adapter
        mAdapter.clear();
        //If there is a valid List of Books add them to the adapter
        // and update the listview
        if (books != null && !books.isEmpty()){
            mAdapter.addAll(books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        //Reset Loader so that we can clear out existing data
        mAdapter.clear();
    }
}

//set an itemOnClick Listener to when the user push the button
//This leads to a request to the browser with the query and  list ob Books as a response

        /*bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //find the Button in the Layout
                Button requestButon = (Button) findViewById(R.id.searchButton);


            }
        });*/