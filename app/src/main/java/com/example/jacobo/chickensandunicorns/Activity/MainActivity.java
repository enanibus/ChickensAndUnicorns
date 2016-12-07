package com.example.jacobo.chickensandunicorns.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.jacobo.chickensandunicorns.Model.Menu;
import com.example.jacobo.chickensandunicorns.R;
import com.example.jacobo.chickensandunicorns.Utils.DownloadMenu;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.downloadMenu();

    }

    protected void downloadMenu() {
        // Check if we need to download courses
        if (Menu.getMenu().size() == 0) {
            DownloadMenu downloadMenu = new DownloadMenu((ViewGroup) findViewById(R.id.activity_main), this);
            downloadMenu.execute();
        }
    }
}
