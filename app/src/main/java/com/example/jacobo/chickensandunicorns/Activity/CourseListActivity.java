package com.example.jacobo.chickensandunicorns.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jacobo.chickensandunicorns.Fragment.CourseListFragment;
import com.example.jacobo.chickensandunicorns.Model.Course;
import com.example.jacobo.chickensandunicorns.Model.Order;
import com.example.jacobo.chickensandunicorns.Model.Table;
import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by jacobo on 8/12/16.
 */

public class CourseListActivity extends AppCompatActivity implements CourseListFragment.OnAddCourseSelectedListener {
    private Order mOrder;
    public static final String TABLE_INDEX="TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
    }

    @Override
    public void onAddCourseSelected(Course course, String extras, Table table) {
        table.getOrder().addCourse(course, extras);
        finish();
    }

}
