package orozdevelopment.fitness.data;

import android.provider.BaseColumns;

/**
 * Created by michael on 5/6/16.
 */
public class DatabaseContract {

    public DatabaseContract(){}

    public static abstract class WeightsTBL implements BaseColumns {
        public static final String TABLE_NAME = "WeightsTBL";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String COLUMN_NAME_WEIGHT = "Weight";
    }
}
