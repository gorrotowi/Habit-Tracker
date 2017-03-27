package com.gorrotowi.habittracker.database;

import android.provider.BaseColumns;

/**
 * Created by Gorro on 09/02/17.
 */

public final class HabitContract {

    public static int NO_TAKE_WATER = 0;
    public static int TAKE_WATER = 1;
    public static int NO_WALK = 0;
    public static int WALK = 1;

    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_TAKE_WATER = "takewater";
        public static final String COLUMN_NAME_WALK = "walk";
    }

}
