package com.example.jacobo.chickensandunicorns.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jacobo.chickensandunicorns.Model.Table;
import com.example.jacobo.chickensandunicorns.Model.Tables;
import com.example.jacobo.chickensandunicorns.R;

/**
 * Created by jacobo on 7/12/16.
 */

public class TableListFragment extends Fragment {
    private OnTableSelectedListener mOnTableSelectedListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Accedemos al ListView
        ListView list = (ListView) root.findViewById(android.R.id.list);

        // Creamos nuestro modelo
        final Tables tables = Tables.getSTables();

        // Creamos un adaptador para poner en común el modelo con la lista
        ArrayAdapter<Table> adapter = new ArrayAdapter<>(
               getActivity(),
                android.R.layout.simple_list_item_1,
                tables.getTables()
        );


        // Le asignamos el adaptador a la lista
        list.setAdapter(adapter);

        // Le asigno un listener a la lista para enterarme de cuándo se ha pulsado una fila
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Avisamos al listener que el usuario ha pulsado una fila
                if (mOnTableSelectedListener != null) {
                    mOnTableSelectedListener.onTableSelected(tables.getTable(position), position);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof  OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof  OnTableSelectedListener) {
            mOnTableSelectedListener = (OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(Table table, int position);
    }
}
