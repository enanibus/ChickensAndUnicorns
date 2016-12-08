package com.example.jacobo.chickensandunicorns.Model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jacobo on 4/12/16.
 */

public class Course implements Serializable {
    private int mIdCourse = -1;
    private String mName = null;
    private String mDescription = null;
    private String mType = null;
    private double mPrice = 0;
    private URL mImageURL = null;
    private String mWishList = null;
    private ArrayList<String> mAllergens = null;

    public Course(int idCourse, String name, String description, String type, double price, URL imageURL, String wishList, ArrayList<String> allergens) {
        mIdCourse = idCourse;
        mName = name;
        mDescription = description;
        mType = type;
        mPrice = price;
        mImageURL = imageURL;
        mWishList = wishList;
        mAllergens = allergens;
    }

    public int getIdCourse() {
        return mIdCourse;
    }

    public void setIdCourse(int idCourse) {
        mIdCourse = idCourse;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public URL getImageURL() {
        return mImageURL;
    }

    public void setImageURL(URL imageURL) {
        mImageURL = imageURL;
    }

    public String getWishList() {
        return mWishList;
    }

    public void setWishList(String wishList) {
        mWishList = wishList;
    }

    public ArrayList<String> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(ArrayList<String> allergens) {
        mAllergens = allergens;
    }
}
