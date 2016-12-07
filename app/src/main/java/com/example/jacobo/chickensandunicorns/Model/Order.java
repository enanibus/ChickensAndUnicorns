package com.example.jacobo.chickensandunicorns.Model;

import java.util.LinkedList;

/**
 * Created by jacobo on 4/12/16.
 */

public class Order {
    private int mIdOrder;
    private int mIdTable;
    private LinkedList<Course> mCourseLinkedList;

    public Order(int idOrder, int idTable, LinkedList<Course> courseLinkedList) {
        mIdOrder = idOrder;
        mIdTable = idTable;
        mCourseLinkedList = courseLinkedList;
    }

    public int getIdOrder() {
        return mIdOrder;
    }

    public void setIdOrder(int idOrder) {
        mIdOrder = idOrder;
    }

    public int getIdTable() {
        return mIdTable;
    }

    public void setIdTable(int idTable) {
        mIdTable = idTable;
    }

    public LinkedList<Course> getCourseLinkedList() {
        return mCourseLinkedList;
    }

    public void setCourseLinkedList(LinkedList<Course> courseLinkedList) {
        mCourseLinkedList = courseLinkedList;
    }
}
