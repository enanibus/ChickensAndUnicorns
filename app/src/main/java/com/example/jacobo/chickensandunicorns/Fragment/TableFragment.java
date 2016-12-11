package com.example.jacobo.chickensandunicorns.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jacobo.chickensandunicorns.Activity.TableDetailActivity;
import com.example.jacobo.chickensandunicorns.Adapter.CourseRecyclerViewAdapter;
import com.example.jacobo.chickensandunicorns.Model.Course;
import com.example.jacobo.chickensandunicorns.Model.Table;
import com.example.jacobo.chickensandunicorns.Model.Tables;
import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by jacobo on 8/12/16.
 */

public class TableFragment extends Fragment{
    private static final String TABLE_INDEX = "TABLE_INDEX";
    private int mTableIndex;
    private CourseRecyclerViewAdapter mAdapter;
//    private Order mOrder;
    Table mTable;

    public static TableFragment newInstance(int position) {
        Bundle arguments = new Bundle();
        arguments.putInt(TABLE_INDEX, position);

        TableFragment fragment = new TableFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null){
            mTableIndex = getArguments().getInt(TABLE_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table, container);

        Tables tables = Tables.getSTables();
        mTableIndex = getActivity().getIntent().getIntExtra(TableDetailActivity.TABLE_INDEX, 0);
        mTable = tables.getTable(mTableIndex);

        RecyclerView listRecycle = (RecyclerView) root.findViewById(R.id.menu_list);
        listRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        listRecycle.setItemAnimator(new DefaultItemAnimator());


        mAdapter = new CourseRecyclerViewAdapter(mTable.getOrder().getCourseLinkedList(), new CourseRecyclerViewAdapter.OnCourseSelectedListener() {
            @Override
            public void onCourseSelected(Course course) {

            }

            @Override
            public void onCourseLongSelected(final Course course) {
                AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
                confirmDialog.setMessage(R.string.remove_course);
                confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.notifyDataSetChanged();
                        mTable.getOrder().removeCourse(course);
                        double amount = mTable.getTheBill() - course.getPrice();
                        mTable.setTheBill(amount);
                        Snackbar
                                .make(getView(), R.string.course_deleted, Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
                confirmDialog.setNegativeButton(android.R.string.cancel, null);
                confirmDialog.show();

            }
        });

        listRecycle.setAdapter(mAdapter);

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableDetailActivity myActivity = (TableDetailActivity) getActivity();
                myActivity.onAddCourseTapped(mTableIndex);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_table, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Tables tables = Tables.getSTables();
        if (item.getItemId() == R.id.order) {

            TableDetailActivity myActivity = (TableDetailActivity) getActivity();
            myActivity.onAddCourseTapped(mTableIndex);

            return true;
        }
        else if (item.getItemId() == R.id.checkout) {
            AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
            confirmDialog.setMessage(getActivity().getString(R.string.total_amount) + tables.getTable(mTableIndex).getTheBill() + getActivity().getString(R.string.checkout_order));
            confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mTable.clear();
                    mAdapter.notifyDataSetChanged();
                    Snackbar
                            .make(getView(), R.string.table_available, Snackbar.LENGTH_SHORT)
                            .show();
                }
            });
            confirmDialog.setNegativeButton(android.R.string.cancel, null);
            confirmDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

