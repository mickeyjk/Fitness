package orozdevelopment.fitness.dashboard.cards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import orozdevelopment.fitness.R;

/**
 * Created by michael on 5/4/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVHolder>{

    List<ICard> cards;

    public RVAdapter(List<ICard> cards){
        this.cards = cards;
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount(){
        return cards.size();
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        RVHolder holder;
        View v;
        Context context = viewGroup.getContext();

        switch (viewType){
            case 0:
                v = LayoutInflater.from(context)
                        .inflate(R.layout.dashboard_basic_info_card, viewGroup, false);
                holder = new BasicInfoCardViewHolder(v);
                break;
            default:
                v = LayoutInflater.from(context)
                        .inflate(R.layout.dashboard_basic_info_card, viewGroup, false);
                holder = new BasicInfoCardViewHolder(v);
                break;
        }

        return holder;
    }

    @Override
    public int getItemViewType(int position){
        return cards.get(position).cardType;
    }

    @Override
    public void onBindViewHolder(RVHolder holder, int index){
        int viewType = getItemViewType(index);

        switch (viewType){
            case 0:
                BasicInfoCard card = (BasicInfoCard)cards.get(index);
                BasicInfoCardViewHolder holder1 = (BasicInfoCardViewHolder) holder;

                holder1.nameView.setText(card.name);
                holder1.ageView.setText(String.valueOf("Age: " + card.age));
                holder1.weightView.setText(String.valueOf("Weight: " + card.weight));
                holder1.heightView.setText(String.valueOf("Height: " + card.height));
                holder1.bmiView.setText(String.valueOf("BMI: " + card.bmi));
                holder1.photoView.setImageResource(card.personalPhotoId);

                break;
        }

    }

}
