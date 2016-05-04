package orozdevelopment.fitness.dashboard.cards;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import orozdevelopment.fitness.R;

/**
 * Created by michael on 5/4/16.
 */
public abstract class RVHolder extends RecyclerView.ViewHolder {
    CardView cv;

    RVHolder(View itemView){
        super(itemView);

        cv = (CardView)itemView.findViewById(R.id.cv);
    }
}
