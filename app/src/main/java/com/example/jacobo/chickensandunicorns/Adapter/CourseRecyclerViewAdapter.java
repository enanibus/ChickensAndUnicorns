package com.example.jacobo.chickensandunicorns.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jacobo.chickensandunicorns.Model.Course;
import com.example.jacobo.chickensandunicorns.R;
import com.example.jacobo.chickensandunicorns.Utils.CourseView;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import static com.example.jacobo.chickensandunicorns.R.id.course_image;

/**
 * Created by jacobo on 7/12/16.
 */

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder> {
    private LinkedList<Course> mCourses;
    private OnCourseSelectedListener mCourseSelectedListener;

    public CourseRecyclerViewAdapter(LinkedList<Course> courses, OnCourseSelectedListener courseSelectedListener){
        mCourses = courses;
        mCourseSelectedListener = courseSelectedListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_course, parent,false);
        return new CourseViewHolder(view, mCourseSelectedListener);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.bindCourse(mCourses.get(position));
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{
        private CourseView mCourseView;
        private Course mCourseData;
        private AsyncTask<Void, Void, Bitmap> mDownloadImageAsyncTask;
        private WeakReference<OnCourseSelectedListener> mOnCourseSelectedListener;

        private TextView mName;
        private TextView mPrice;
        private TextView mDescription;
        private TextView mWishList;
        private ImageView mImage;
        private View mView;

        public CourseViewHolder(View itemView, OnCourseSelectedListener courseSelectedListener) {
            super(itemView);

            mView = itemView;

            mName = (TextView) itemView.findViewById(R.id.course_name);
            mPrice = (TextView) itemView.findViewById(R.id.course_price);
            mDescription = (TextView) itemView.findViewById(R.id.course_customization);
            mWishList = (TextView) itemView.findViewById(R.id.course_suggestion);
            mImage = (ImageView) itemView.findViewById(course_image);

            mOnCourseSelectedListener = new WeakReference<OnCourseSelectedListener>(courseSelectedListener);
        }

//        public CourseViewHolder(View itemView, OnCourseSelectedListener courseSelectedListener) {
//            super(itemView);
//
//            mCourseView = (CourseView) itemView.findViewById(R.id.course);
//            mOnCourseSelectedListener = new WeakReference<OnCourseSelectedListener>(courseSelectedListener);
//        }

        public void bindCourse(final Course course){
            mCourseData = course;
            mName.setText(course.getName());

            mDescription.setText(course.getDescription());

            Double price = course.getPrice();
            String priceString = String.format("%.2f",price);
            String priceFormated = "Precio: " + priceString  + " €";
            mPrice.setText(priceFormated);
            mWishList.setText(course.getWishList());

            if (mOnCourseSelectedListener.get() != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCourseData != null) {
                            mOnCourseSelectedListener.get().onCourseSelected(mCourseData);
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mCourseData != null) {
                            mOnCourseSelectedListener.get().onCourseLongSelected(mCourseData);
                        }
                        return true;
                    }
                });
            }

//            mCourseData.setImage(new AsyncImage(mCourseData.getImageURL()));
//            mCourseData.getImage().downloadImage();

            // Start download image if needed
            // Cancel previous task if exists
            if (mDownloadImageAsyncTask != null) {
                mDownloadImageAsyncTask.cancel(true);
            }
            mDownloadImageAsyncTask = new AsyncTask<Void, Void, Bitmap>() {
                Context mContext;

                @Override
                protected void onPreExecute() {
                    mContext = mImage.getContext();
//                    mImage.setImageResource(Integer.parseInt(null));
                }

                @Override
                protected Bitmap doInBackground(Void... params) {
                    return mCourseData.getBitmap(mContext);
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    if (!isCancelled()) { // Check if was cancelled, if so don't set the image
                        if (bitmap != null) {

                            mImage.setImageBitmap(bitmap);
                                mImage.setVisibility(View.VISIBLE);
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
                }
            };

            mDownloadImageAsyncTask.execute();



        }
    }

    // Interface to notify when a course has been selected
    public interface OnCourseSelectedListener {
        void onCourseSelected(Course course);
        void onCourseLongSelected(Course course);
    }
}

