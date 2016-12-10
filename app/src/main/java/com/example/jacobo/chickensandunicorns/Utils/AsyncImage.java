package com.example.jacobo.chickensandunicorns.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by jacobo on 10/12/16.
 */

public class AsyncImage {
    private URL mImageURL;

    public ImageView getImage() {
        return mImage;
    }

    private ImageView mImage;
    private AsyncTask<Void, Void, Bitmap> mDownloadImage;
//    private ProgressDialog mProgressDialog = new ProgressDialog(mDownloadImage);

    public AsyncImage(URL imageURL) {
        mImageURL = imageURL;
    }

    public void setImage(Bitmap bitmap) {
        if (bitmap != null) {
            mImage.setImageBitmap(bitmap);
            if (mImage.getVisibility() == View.GONE) {
                mImage.setVisibility(View.VISIBLE);
                // Animate the transition to visible for a better effect
                mImage.setAlpha(0.0f);
                mImage.animate()
                        .setDuration(1)
                        .alpha(1.0f);
            }
        }
        else {
            mImage.setVisibility(View.GONE);
        }
    }


    private Bitmap getBitmapFromURL(String url) {
//        Log.v("MainCourse",url);
//        File imageFile = new File(context.getCacheDir(), getName());
//
//        // We check if file already exists
//        if (imageFile.exists()) {
//            return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
//        }

        InputStream in = null;
        try {
            in = new java.net.URL(url).openStream();
            Bitmap bmp = BitmapFactory.decodeStream(in);
            return bmp;
//            if (bmp != null) {
//                FileOutputStream fos = new FileOutputStream(imageFile);
//                bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
//                fos.close();
//                return bmp;
//            }
//            else {
//                return null;
//            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error downloading image", e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadImage() {

        // Start download image if needed
        // Cancel previous task if exists
        if (mDownloadImage != null) {
            mDownloadImage.cancel(true);
        }
        mDownloadImage = new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected void onPreExecute() {

//                mProgressDialog.setTitle(this.get().getString(R.string.downloading));
//                mProgressDialog.show();
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                return getBitmapFromURL(mImageURL.toString());
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {

                try {
//                    mProgressDialog.dismiss();
                } catch (IllegalArgumentException ex) {

                    if (!isCancelled()) {
                        mImage.setImageBitmap(bitmap);
                    }
                }
            }
        };

        mDownloadImage.execute();
    }

    public URL getImageURL() {
        return mImageURL;
    }

    public void setImageURL(URL imageURL) {
        mImageURL = imageURL;
    }

    public void setImage(ImageView image) {
        mImage = image;
    }

}
