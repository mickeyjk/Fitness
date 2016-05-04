package orozdevelopment.fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import orozdevelopment.fitness.dashboard.cards.BasicInfoCard;
import orozdevelopment.fitness.dashboard.cards.ICard;
import orozdevelopment.fitness.dashboard.cards.RVAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    private List<ICard> cards;
    private RecyclerView rv;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DashboardFragment.
     */
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            return;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        rv = (RecyclerView)v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeData();
        initializeAdapter();

        return v;
    }


    //TODO: Reevaluate how cards are initialized
    private void initializeData(){
        cards = new ArrayList<ICard>();

        cards.add(new BasicInfoCard("Michael Cohen", 21, 170, 160, 0.18, R.drawable.ic_menu_gallery));
        cards.add(new BasicInfoCard("Or Oz", 21, 169, 170, 0.5, R.drawable.ic_menu_gallery));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }
}
