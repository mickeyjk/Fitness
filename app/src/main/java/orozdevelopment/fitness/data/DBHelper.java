package orozdevelopment.fitness.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michael on 5/6/16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String FLOAT_TYPE = " REAL";
    private static final String LONG_TYPE = " BIGINT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseContract.WeightsTBL.TABLE_NAME + " (" +
                    DatabaseContract.WeightsTBL._ID + " INTEGER PRIMARY KEY," +
                    DatabaseContract.WeightsTBL.COLUMN_NAME_DATE + FLOAT_TYPE + COMMA_SEP +
                    DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT + LONG_TYPE + COMMA_SEP +
            " )";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "fitnessDB.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatabaseContract.WeightsTBL.TABLE_NAME;

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
