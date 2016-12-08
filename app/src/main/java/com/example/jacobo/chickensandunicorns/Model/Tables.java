package com.example.jacobo.chickensandunicorns.Model;

import java.util.LinkedList;

/**
 * Created by jacobo on 8/12/16.
 */

public class Tables {
    private LinkedList<Table> mTables;
    private static Tables sTables;
    private static final int NUMBER_OF_TABLES = 10;

    public static Tables getSTables() {
        // Creación perezosa
        if (sTables == null) {
            Tables.sTables = new Tables();
        }
        return Tables.sTables;
    }

    private Tables() {
        this.clear();
    }

    private void clear() {
        mTables = new LinkedList<Table>();

        for (int i = 0; i < NUMBER_OF_TABLES; i++) {
            mTables.add(new Table(i, null, false, 0.0));
        }
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public Table getTable(int position) {
        return mTables.get(position);
    }
}
