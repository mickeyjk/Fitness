package orozdevelopment.fitness;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;

import orozdevelopment.fitness.data.DBHelper;
import orozdevelopment.fitness.data.DatabaseContract;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightTrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightTrackingFragment extends Fragment {

    private TextView tv;
    private HashMap<Date, Float> weights;

    public WeightTrackingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WeightTrackingFragment.
     */
    public static WeightTrackingFragment newInstance() {
        WeightTrackingFragment fragment = new WeightTrackingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_weight_tracking, container, false);

        //Get Weight Data
        Context context = container.getContext();
        this.weights = readData(context);

        tv = (TextView)v.findViewById(R.id.weight_text_view);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewWeight();
            }
        });

        return v;
    }

    public HashMap<Date, Float> readData(Context c){

        DBHelper helper = new DBHelper(c);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
          DatabaseContract.WeightsTBL.COLUMN_NAME_DATE,
                DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT
        };

        String sortOrder = DatabaseContract.WeightsTBL.COLUMN_NAME_DATE + " ASC";
        Cursor cursor = db.query(
                DatabaseContract.WeightsTBL.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        HashMap<Date, Float> w = new HashMap<Date, Float>();

        cursor.moveToFirst();
        while (!cursor.moveToNext()){
            Long millis = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DatabaseContract.WeightsTBL.COLUMN_NAME_DATE)
            );

            Date date = new Date(millis);
            Float weight = cursor.getFloat(
                    cursor.getColumnIndexOrThrow(DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT)
            );

            w.put(date, weight);
        }

        return w;
    }

    public long writeData(Context c, String textViewData){

        DBHelper helper = new DBHelper(c);
        SQLiteDatabase db = helper.getWritableDatabase();

        Float weight = Float.parseFloat(textViewData);
        Date d = new Date(System.currentTimeMillis());
        long date = d.getTime();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.WeightsTBL.COLUMN_NAME_DATE, date);
        values.put(DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT, weight);

        long rowID = db.insert(
                DatabaseContract.WeightsTBL.TABLE_NAME,
                "null",
                values
        );

        return rowID;
    }

    public void addNewWeight(){
        
    }

}
