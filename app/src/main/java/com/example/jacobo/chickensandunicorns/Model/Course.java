package com.example.jacobo.chickensandunicorns.Model;

import android.graphics.Bitmap;

import com.example.jacobo.chickensandunicorns.Utils.AsyncImage;

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

    // wrapper class
    private AsyncImage mImage;
    private Bitmap mBitmap;

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

    public AsyncImage getImage() {
        return mImage;
    }

    public void setImage(AsyncImage image) {
        mImage = image;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

//    public Bitmap getBitmap(Context context) {
//        return getBitmapFromURL(context, mImageURL.toString());
//    }
//
//    public Bitmap getBitmapFromURL(Context context, String url) {
//        Log.v("Course",url);
//        File imageFile = new File(context.getCacheDir(), getName());
//
//        // We check if file already exists
//        if (imageFile.exists()) {
//            return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//        }
//
//        InputStream in = null;
//        try {
//            in = new java.net.URL(url).openStream();
//            Bitmap bmp = BitmapFactory.decodeStream(in);
//            if (bmp != null) {
//                FileOutputStream fos = new FileOutputStream(imageFile);
//                bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
//                fos.close();
//                return bmp;
//            }
//            else {
//                return null;
//            }
//        } catch (Exception e) {
//            Log.e(context.getString(R.string.app_name), "Error downloading image", e);
//            return null;
//        } finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
