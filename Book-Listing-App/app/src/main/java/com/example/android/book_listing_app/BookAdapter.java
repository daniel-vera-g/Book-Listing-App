package com.example.android.book_listing_app;

/**
 * Created by DanielVG on 06.08.17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        //check if an existing ListItemView is there to reuse
        //if not create one
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.booklist_activity, parent, false);
        }
        //find Book at the given position at the list of Books
        Book currentBook = getItem(position);

        //find Textview with the view ID Title
        TextView title = (TextView) listItemView.findViewById(R.id.bookTitle);
        //Display the Title of the current Book in that Textview
        title.setText(currentBook.getmTitle());

        //find Textview with the view ID subtitle
        TextView subtitle = (TextView) listItemView.findViewById(R.id.subtitle);
        //Display the subtitle of the current Book in that Textview
        subtitle.setText(currentBook.getmSubtitle());

        //find Textview with the view ID author
        TextView author = (TextView) listItemView.findViewById(R.id.authors);
        //Display the author of the current Book in that Textview
        author.setText(currentBook.getmAuthor());

        //find Textview with the view ID publishedDate
        TextView publishedDate = (TextView) listItemView.findViewById(R.id.publishedDate);
        //Display the published date of the current Book in that Textview
        publishedDate.setText(currentBook.getmAuthor());

        //find Textview with the view ID numberPages
        TextView numberOfPages = (TextView) listItemView.findViewById(R.id.pageCount);
        //Display the number of pages of the current Book in that Textview
        numberOfPages.setText(currentBook.getmNumberOfPages());

        return listItemView;
    }
}
