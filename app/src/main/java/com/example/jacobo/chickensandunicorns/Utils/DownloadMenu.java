package com.example.jacobo.chickensandunicorns.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.view.ViewGroup;

import com.example.jacobo.chickensandunicorns.Model.Course;
import com.example.jacobo.chickensandunicorns.Model.Menu;
import com.example.jacobo.chickensandunicorns.R;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created by jacobo on 6/12/16.
 */

public class DownloadMenu extends AsyncTask<Void, Void, LinkedList<Course>> {
    private ProgressDialog mProgressDialog;
    private ViewGroup mContainer;
    private WeakReference<Activity> mActivity;


    public DownloadMenu(ViewGroup container, Activity activity) {
        super();
        mContainer = container;
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        mContainer.setAlpha(0.0f);

        mProgressDialog = new ProgressDialog(mActivity.get());
        mProgressDialog.setTitle(mActivity.get().getString(R.string.downloading));
        mProgressDialog.show();
    }

    @Override
    protected LinkedList<Course> doInBackground(Void... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Menu.downloadMenu();
    }

    @Override
    protected void onPostExecute(LinkedList<Course> courses) {
        try {
            mProgressDialog.dismiss();
        } catch (IllegalArgumentException ex) {
        }
        if (courses == null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity.get());
            alertDialog.setTitle(R.string.error);
            alertDialog.setMessage(R.string.error_downloading_menu);
            alertDialog.setCancelable(false);
            alertDialog.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mActivity.get().finish();
                }
            });
            alertDialog.show();
        }
        else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContainer.animate()
                    .setDuration(mContainer.getContext().getResources().getInteger(R.integer.default_animation_duration))
                    .alpha(1.0f);
        }
    }
}