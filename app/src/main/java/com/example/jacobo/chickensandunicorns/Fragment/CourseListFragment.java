package com.example.jacobo.chickensandunicorns.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jacobo.chickensandunicorns.Activity.CourseListActivity;
import com.example.jacobo.chickensandunicorns.Adapter.CourseRecyclerViewAdapter;
import com.example.jacobo.chickensandunicorns.Model.Course;
import com.example.jacobo.chickensandunicorns.Model.Menu;
import com.example.jacobo.chickensandunicorns.Model.Table;
import com.example.jacobo.chickensandunicorns.Model.Tables;
import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by jacobo on 8/12/16.
 */

public class CourseListFragment extends Fragment {

    private OnAddCourseSelectedListener mOnCourseSelectedListener;
    private static final String TABLE_INDEX="TABLE_INDEX";
    private int mTableIndex;
    private Tables mTables;
    private Table mTable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(false);

        if (getArguments() != null) {
            mTableIndex = getArguments().getInt(TABLE_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_course_list, container);

        mTables = Tables.getSTables();
        mTableIndex = getActivity().getIntent().getIntExtra(CourseListActivity.TABLE_INDEX, 0);
        mTable = mTables.getTable(mTableIndex);

        RecyclerView list = (RecyclerView) root.findViewById(R.id.menu_list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(new CourseRecyclerViewAdapter(Menu.getMenu(), new CourseRecyclerViewAdapter.OnCourseSelectedListener() {
            @Override
            public void onCourseSelected(final Course course) {
//                Log.v("Borrando Plato", course.getName());
                AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
                final EditText input = new EditText(getActivity());
                confirmDialog.setTitle(R.string.course_wishlist);
                confirmDialog.setView(input);
                confirmDialog.setMessage(R.string.add_extras);
                confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String suggestions = input.getText().toString();
                        mOnCourseSelectedListener.onAddCourseSelected(course,suggestions, mTable);
                    }
                });
                confirmDialog.show();
            }

            @Override
            public void onCourseLongSelected(Course course) {
            }
        }));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachToActivity(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachToActivity(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnCourseSelectedListener = null;
    }

    private void attachToActivity(Context context) {
        if (!(context instanceof OnAddCourseSelectedListener)) {
            throw new ClassCastException("Activity holding CourseSelectorFragment should implement OnCourseOrderSelectedListener");
        }

        mOnCourseSelectedListener = (OnAddCourseSelectedListener) context;
    }

    public interface OnAddCourseSelectedListener {
        void onAddCourseSelected(Course course, String wishlist, Table table);
    }
}

