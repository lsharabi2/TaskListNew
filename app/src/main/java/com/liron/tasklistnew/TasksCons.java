package com.liron.tasklistnew;

import android.net.Uri;

public class TasksCons {

    public final static String PACKAGE = "com.liron.tasklistnew";

    public final static String TABLE_NAME = "Tasks";

    public final static Uri CONTENT_URI = Uri.parse("content://" + PACKAGE + "/" + TABLE_NAME);

    public final static String _ID = "_id";

    public final static String DESC = "description";

    public final static String IS_DONE = "is_done";

    //for later
    public final static String TIME = "time";

    public final static String LAT = "lat";

    public final static String LNG = "lng";
}
