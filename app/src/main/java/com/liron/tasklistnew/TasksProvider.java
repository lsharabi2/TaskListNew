package com.liron.tasklistnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TasksProvider
{
    private DBHelper helper;

    public TasksProvider(Context context)
    {
        helper = new DBHelper(context);
    }

    public void update(Task task)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.updateWithOnConflict(TasksCons.TABLE_NAME, taskToValues(task), TasksCons._ID
                        + " =?", new String[]{ Long.toString(task.getId()) },
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void insert(Task task)
    {
        if (exists(task))
        {
            update(task);
        }
        else
        {
            SQLiteDatabase db = helper.getWritableDatabase();

            Long id = db.insertOrThrow(TasksCons.TABLE_NAME, null, taskToValues(task));
            task.setId(id);
            db.close();
        }
    }

    public void delete(Task task)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(TasksCons.TABLE_NAME, TasksCons._ID + " =?",
                new String[]{ Long.toString(task.getId()) });
        db.close();
    }

    public Cursor Qall()
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TasksCons.TABLE_NAME, null, null, null, null,
                null, TasksCons._ID + " COLLATE LOCALIZED ASC");
        return cursor;
    }

    public Task query(String id)
    {

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TasksCons.TABLE_NAME, null, TasksCons._ID
                + " =?", new String[]{id}, null, null, null);

        Task task = null;

        if (cursor.moveToNext())
        {
            task = new Task(cursor);
        }

        cursor.close();

        return task;
    }

    public boolean exists(Task t)
    {

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(TasksCons.TABLE_NAME, null, TasksCons._ID
                + " =?", new String[]{ Long.toString(t.getId()) }, null, null, null);

        Task task = null;

        if (cursor.moveToNext())
        {
            task = new Task(cursor);
        }

        cursor.close();

        return task != null;
    }

    public void close()
    {
        helper.close();
    }

    private ContentValues taskToValues(Task task)
    {
        ContentValues values = new ContentValues();
        values.put(TasksCons.DESC, task.getDesc());
        values.put(TasksCons.IS_DONE, task.isDone());
        return values;
    }

}
