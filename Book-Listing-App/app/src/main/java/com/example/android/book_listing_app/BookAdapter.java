package com.example.android.book_listing_app;

/**
 * Created by DanielVG on 06.08.17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Knows how to create a List item layout for each Book in the data Source
 */
public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * Constructs a new Earthquake Adapter
     * @param context of the app
     * @param books is the list of Books which is the data Source for the App
     */
    public BookAdapter(Context context, List<Book> books){
        super(context, 0, books);
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return List item view that displays information about the given Book Book in the list of Books
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
