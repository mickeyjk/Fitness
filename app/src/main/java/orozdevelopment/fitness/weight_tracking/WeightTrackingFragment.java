package orozdevelopment.fitness.weight_tracking;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import orozdevelopment.fitness.R;
import orozdevelopment.fitness.data.DBHelper;
import orozdevelopment.fitness.data.DatabaseContract;
import orozdevelopment.fitness.weight_tracking.weight_tracking.cards.RVAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeightTrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeightTrackingFragment extends Fragment implements AddWeightDialogFragment.AddWeightDialogListener, CardView.OnClickListener {

    private RecyclerView rv;
    private RVAdapter adapter;

    ArrayList<Date> dates;
    ArrayList<Float> weights;

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

        rv = (RecyclerView) v.findViewById(R.id.rv_weight);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        readData(context);

        this.adapter = new RVAdapter(dates, weights, this);
        rv.setAdapter(this.adapter);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWeightDialog();
            }
        });

        return v;
    }


    /*
     * RECYCLER VIEW METHODS
     */
    private void updateWeightsList(){
        readData(getContext());
        adapter.setDates(this.dates);
        adapter.setWeights(this.weights);
        adapter.notifyDataSetChanged();
    }

    //CardView.OnClickListener implemented in this class because change in data had to propagate to the date and weight arrays here somehow
    //TODO: Refactor the way data is handled between RVAdapter and WeightTrackingFragment
    @Override
    public void onClick(View v){
        Integer index = (Integer)v.getTag();

        adapter.deleteData(v.getContext(), index);
        dates.remove(index);
        weights.remove(index);

        adapter.notifyItemRemoved(index);
        adapter.notifyItemRangeChanged(index, adapter.getItemCount());

    }

    /*
     * DATABASE OPERATIONS
     */
    private void readData(Context c){

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

        dates = new ArrayList<Date>();
        weights = new ArrayList<Float>();

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Long millis = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DatabaseContract.WeightsTBL.COLUMN_NAME_DATE)
            );

            Date date = new Date(millis);
            Float weight = cursor.getFloat(
                    cursor.getColumnIndexOrThrow(DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT)
            );

            dates.add(date);
            weights.add(weight);
        }


    }

    private long writeData(Context c, String textViewData){

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


    /*
     * ADD WEIGHT DIALOG METHODS / LISTENER IMPLEMENTATION
     */
    public void showAddWeightDialog() {
        System.out.println("Begin Show Add Dialog");

        AddWeightDialogFragment fragment = AddWeightDialogFragment.newInstance(this);
        fragment.show(getFragmentManager(), "add_weight_dialog");

    }


    public void onDialogPositiveClick(AddWeightDialogFragment dialog){
        EditText editText = dialog.weightInput;

        writeData(getContext(), editText.getText().toString());
        updateWeightsList();
    }
    public void onDialogNegativeClick(AddWeightDialogFragment dialog){
        EditText editText = dialog.weightInput;
        editText.setText("");
        updateWeightsList();

    }




}
