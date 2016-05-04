package orozdevelopment.fitness.dashboard.cards;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import orozdevelopment.fitness.R;

/**
 * Created by michael on 5/4/16.
 */
public class BasicInfoCardViewHolder extends RVHolder {
    TextView nameView;
    TextView ageView;
    TextView weightView;
    TextView heightView;
    TextView bmiView;
    ImageView photoView;

    BasicInfoCardViewHolder(View itemView){
        super(itemView);

        nameView = (TextView)itemView.findViewById(R.id.person_name);
        ageView = (TextView)itemView.findViewById(R.id.person_age);
        weightView = (TextView)itemView.findViewById(R.id.person_weight);
        heightView = (TextView)itemView.findViewById(R.id.person_height);
        bmiView = (TextView)itemView.findViewById(R.id.person_bmi);
        photoView = (ImageView)itemView.findViewById(R.id.person_photo);
    }

}
