package com.example.android.book_listing_app;

import android.app.usage.UsageEvents;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    /**
     * Variables
     * */
    //log tag for the logs
    private static final String LOG_TAG = MainActivity.class.getName();
    //url to make the request to
    public static final String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklist_activity);

        //find a reference for the listView file
        //ListView bookListView = (ListView) findViewById(R.id.list_item);
    }

    /**
     * update UI with the given Book
     * */


    /**
     * Perform the network request on a Backround Thread
     * Then Update the UI with the fist Book info I receive
     * */
/*
    private class BookAsyncTask extends AsyncTask<String, Void, Event>{
        @Override
        protected Event doInBackground(String... urls) {
            //if urls is empty don't perform http request
            if (urls.length < 1 || urls[0] == null){
                return null;
            }
            else {
                //perform http request for the book data and fetch the response


            }
        }

        @Override
        protected void onPostExecute(UsageEvents.Event event) {
            super.onPostExecute(event);
        }
    }
*/
}
