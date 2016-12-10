package com.example.jacobo.chickensandunicorns.Activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jacobo.chickensandunicorns.Fragment.TableFragment;
import com.example.jacobo.chickensandunicorns.Model.Tables;
import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by jacobo on 8/12/16.
 */

public class TableDetailActivity extends AppCompatActivity {
    public static final String TABLE_INDEX = "TABLE_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Bundle b = getIntent().getExtras();
            if(b != null) {
                int position = b.getInt(TABLE_INDEX);
                actionBar.setTitle(Tables.getSTables().getTable(position).toString());
            }
        }

        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(R.id.table_fragment) == null){

            int indexTable = getIntent().getIntExtra(TABLE_INDEX,0);

            fm.beginTransaction()
                    .add(R.id.table_fragment, TableFragment.newInstance(indexTable))
                    .commit();
        }
    }

    public void onAddCourseTapped(int tableIndex){
        Intent intent = new Intent(this, CourseListActivity.class);
        intent.putExtra(TableDetailActivity.TABLE_INDEX,tableIndex);
        startActivity(intent);
    }
}
