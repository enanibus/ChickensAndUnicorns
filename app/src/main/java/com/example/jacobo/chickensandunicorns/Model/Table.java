package com.example.jacobo.chickensandunicorns.Model;

/**
 * Created by jacobo on 4/12/16.
 */

public class Table {
    private int mIdTable = -1;
    private Order mOrder = null;
    private boolean mIsAttended = false;
    private double mTheBill = 0;

    public Table(int idTable, Order order, boolean isAttended, double theBill) {
        mIdTable = idTable;
        mOrder = order;
        mIsAttended = isAttended;
        mTheBill = theBill;
    }

    public int getIdTable() {
        return mIdTable;
    }

    public void setIdTable(int idTable) {
        mIdTable = idTable;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    public boolean isAttended() {
        return mIsAttended;
    }

    public void setAttended(boolean attended) {
        mIsAttended = attended;
    }

    public double getTheBill() {
        return mTheBill;
    }

    public void setTheBill(double theBill) {
        mTheBill = theBill;
    }

    @Override
    public String toString() {
        return "Table - " + this.getIdTable();
    }
}
