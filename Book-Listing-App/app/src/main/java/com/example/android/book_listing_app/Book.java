package com.example.android.book_listing_app;

/**
 * Created by DanielVG on 02.08.17.
 */

/**
 * This class contains Information about each Book
 * */
public class Book {

    //name of the author
    private String mAuthor;
    //title of the Book
    private String mTitle;
    //subtitle of the book
    private String mSubtitle;
    //published day of the Book
    private String mPublishedDate;
    // number of pages of the book
    private int mNumberOfPages;
    //link to see a preview of the Book
    private String mPreviewLink;

    /**
     *
     * @param author
     * @param title
     * @param publishedDate
     * @param numberPages
     * @param previewLink
     */
    public Book(String author, String title, String publishedDate, int numberPages, String previewLink){
        mAuthor = author;
        mTitle = title;
        mPublishedDate = publishedDate;
        mNumberOfPages = numberPages;
        mPreviewLink = previewLink;
    }

    /**
     *
     * @return auhtor of the Book
     */
    public String getmAuthor() {
        return mAuthor;
    }

    /**
     *
     * @return title of the book
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     *
     * @return publishedDate of the book
     */
    public String getmPublishedDate() {
        return mPublishedDate;
    }

    /**
     *
     * @return number of pages of the book
     */
    public int getmNumberOfPages() {
        return mNumberOfPages;
    }

    /**
     *
     * @return previewLink of the Book
     */
    public String getmPreviewLink() {
        return mPreviewLink;
    }
}
