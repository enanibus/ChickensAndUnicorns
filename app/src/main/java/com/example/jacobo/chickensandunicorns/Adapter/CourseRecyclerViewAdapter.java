package com.example.jacobo.chickensandunicorns.Adapter;

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
        private Course mCourse;
        private AsyncTask<Void, Void, Bitmap> mDownloadImageAsyncTask;
        private WeakReference<OnCourseSelectedListener> mOnCourseSelectedListener;

        private TextView mName;
        private TextView mPrice;
        private TextView mDescription;
        private TextView mWishList;
        private ImageView mImage;

        public CourseViewHolder(View itemView, OnCourseSelectedListener courseSelectedListener) {
            super(itemView);
            mOnCourseSelectedListener = new WeakReference<>(courseSelectedListener);

            mName = (TextView) itemView.findViewById(R.id.course_name);
            mImage = (ImageView) itemView.findViewById(course_image);
            mDescription = (TextView) itemView.findViewById(R.id.course_description);
            mPrice = (TextView) itemView.findViewById(R.id.course_price);
            mWishList = (TextView) itemView.findViewById(R.id.course_wishlist);

        }

        public void bindCourse(final Course course){
            String units = " $";
            mCourse = course;
            mName.setText(course.getName());
            mDescription.setText(course.getDescription());
            mPrice.setText(String.format("%.2f %s", course.getPrice(), units));
            mWishList.setText(course.getWishList());

            mImage.setImageBitmap(mCourse.getBitmap());
            mImage.setVisibility(View.VISIBLE);

            if (mOnCourseSelectedListener.get() != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCourse != null) {
                            mOnCourseSelectedListener.get().onCourseSelected(mCourse);
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mCourse != null) {
                            mOnCourseSelectedListener.get().onCourseLongSelected(mCourse);
                        }
                        return true;
                    }
                });
            }
        }
    }

    public interface OnCourseSelectedListener {
        void onCourseSelected(Course course);
        void onCourseLongSelected(Course course);
    }
}

