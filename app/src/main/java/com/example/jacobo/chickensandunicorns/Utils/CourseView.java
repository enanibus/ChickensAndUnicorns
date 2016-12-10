package com.example.jacobo.chickensandunicorns.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by idenjoe on 03/05/16.
 */
public class CourseView extends ImageView {

    private final ProgressBar mImageDownloadProgress;

    public ImageView getImage() {
        return mImage;
    }

    private ImageView mImage;


    public CourseView(Context context) {
        this(context, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }



    public CourseView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create the layout from XML
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.view_main_course, this, true);

        mImage = (ImageView) findViewById(R.id.course_image);
        mImageDownloadProgress = (ProgressBar) findViewById(R.id.image_download_progress);

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
            mImageDownloadProgress.setVisibility(View.GONE);
        }
        else {
            mImage.setVisibility(View.GONE);
            mImageDownloadProgress.setVisibility(View.VISIBLE);
        }
    }
}
