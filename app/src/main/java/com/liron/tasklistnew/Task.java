package com.liron.tasklistnew;

import android.database.Cursor;

import java.io.Serializable;

/**
 * Created by Admin on 07/12/2015.
 */
public class Task implements Serializable
{
    private long id;
    private String desc;
    private Boolean is_done;

    public Task()
    {
        this.id = 0;
        this.is_done = false;
    }

    public Task(String desc, Boolean is_done)
    {
        this.id = 0;
        this.desc = desc;
        this.is_done = is_done;
    }

    public Task(Cursor cursor)
    {
        this.id = cursor.getInt(cursor.getColumnIndex(TasksCons._ID));
        this.desc = cursor.getString(cursor.getColumnIndex(TasksCons.DESC));
        this.is_done = cursor.getInt(cursor.getColumnIndex(TasksCons.IS_DONE)) > 0 ? true : false;
    }

    @Override
    public String toString() {
        return desc + " " + is_done;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Boolean isDone()
    {
        return is_done;
    }

    public void setIsDone(Boolean is_done)
    {
        this.is_done = is_done;
    }

    @Override
    public boolean equals(Object o) {
        if(! (o instanceof Task))
            return false;
        Task other = (Task) o;
        return id == other.id;
    }
}
