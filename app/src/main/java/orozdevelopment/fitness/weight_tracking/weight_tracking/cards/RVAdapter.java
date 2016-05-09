package orozdevelopment.fitness.weight_tracking.weight_tracking.cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import orozdevelopment.fitness.R;
import orozdevelopment.fitness.dashboard.cards.ICard;
import orozdevelopment.fitness.dashboard.cards.RVHolder;
import orozdevelopment.fitness.data.DBHelper;
import orozdevelopment.fitness.data.DatabaseContract;

/**
 * Created by michael on 5/8/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.WeightHolder> {

    public static class WeightHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView weightText, dateText;
        int position;

        WeightHolder(View itemView){
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cv_weight);
            weightText = (TextView)cv.findViewById(R.id.weight_card_data);
            dateText = (TextView)cv.findViewById(R.id.weight_card_timestamp);
            position = getAdapterPosition();

        }


    }



    ArrayList<Date> dates;
    ArrayList<Float> weights;
    CardView.OnClickListener mListener;

    public RVAdapter(ArrayList<Date> dates, ArrayList<Float> weights, CardView.OnClickListener listener) {
        this.dates = dates;
        this.weights = weights;
        this.mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    @Override
    public WeightHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();

        View v = LayoutInflater.from(context)
                    .inflate(R.layout.weight_tracking_card, viewGroup, false);
        WeightHolder holder = new WeightHolder(v);


        return holder;
    }

    @Override
    public void onBindViewHolder(WeightHolder holder, int index){

        holder.dateText.setText(dates.get(index).toString());
        holder.weightText.setText(weights.get(index).toString());
        holder.cv.setOnClickListener(mListener);
        holder.cv.setTag(index);
    }




    public void deleteData(Context c, int index){

        String date = Long.toString(dates.get(index).getTime());
        String weight = Float.toString(weights.get(index));
        DBHelper helper = new DBHelper(c);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = DatabaseContract.WeightsTBL.COLUMN_NAME_DATE + "= ? AND "
                + DatabaseContract.WeightsTBL.COLUMN_NAME_WEIGHT + "= ?";
        String[] args = {date, weight};

        db.delete(DatabaseContract.WeightsTBL.TABLE_NAME, where, args);
        dates.remove(index);
        weights.remove(index);
    }


    public void setDates(ArrayList<Date> d){
        this.dates = d;
    }

    public void setWeights(ArrayList<Float> w){
        this.weights = w;
    }

}
