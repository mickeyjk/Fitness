package orozdevelopment.fitness;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import orozdevelopment.fitness.dashboard.cards.LastWorkoutCard;
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

        rv = (RecyclerView)v.findViewById(R.id.rv_dashboard);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dashboard FAB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeData();
        initializeAdapter();

        return v;
    }


    //TODO: Reevaluate how cards are initialized
    private void initializeData(){
        cards = new ArrayList<ICard>();

        cards.add(new BasicInfoCard("Michael Cohen", 21, 170, 160, 0.18, R.drawable.ic_menu_gallery));
        cards.add(new LastWorkoutCard());

        cards.add(new BasicInfoCard("Or Oz", 21, 170, 160, 0.18, R.drawable.ic_menu_gallery));
        cards.add(new LastWorkoutCard());

        cards.add(new BasicInfoCard("Michael Cohen", 21, 170, 160, 0.18, R.drawable.ic_menu_gallery));
        cards.add(new LastWorkoutCard());

        cards.add(new BasicInfoCard("Or OZ", 21, 170, 160, 0.18, R.drawable.ic_menu_gallery));
        cards.add(new LastWorkoutCard());

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(cards);
        rv.setAdapter(adapter);
    }
}
