package net.maker.wonho.soccerschedule.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.idunnololz.widgets.AnimatedExpandableListView;

import net.maker.wonho.soccerschedule.MainActivity;
import net.maker.wonho.soccerschedule.R;
import net.maker.wonho.soccerschedule.schedule.list.data.LeagueData;
import net.maker.wonho.soccerschedule.schedule.list.view.AnimatedExpListAdapter;
import net.maker.wonho.soccerschedule.schedule.list.data.form.ChildItem;
import net.maker.wonho.soccerschedule.schedule.list.data.form.GroupItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wonho Lee on 2015-04-08.
 */
public class ScheduleFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final int defaultSectionNumber = 1;

    private AnimatedExpandableListView aniExpandListView;
    private AnimatedExpListAdapter animatedExpListAdapter;

    public static ScheduleFragment newInstance(int position){
        ScheduleFragment scheduleFragment = new ScheduleFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        scheduleFragment.setArguments(args);

        return scheduleFragment;
    }

    public int getPosition() {
        return getArguments().getInt(ARG_SECTION_NUMBER, defaultSectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View scheduleView = inflater.inflate(R.layout.fragment_schedule, container, false);

        List<GroupItem> items = new ArrayList<GroupItem>();

        // Populate our list with groups and it's children
        for(int i = 1; i < 10; i++) {
            GroupItem item = new GroupItem();

            item.title = "Group " + i;
            if(i%4 == 0){
                item.leagueLogoURL = LeagueData.SerieA_URL;
            }
            else if(i%4 == 1) {
                item.leagueLogoURL = LeagueData.PremierLeague_URL;
            }
            else if(i%4 == 2) {
                item.leagueLogoURL = LeagueData.Bundesliga_URL;
            }
            else if(i%4 == 3) {
                item.leagueLogoURL = LeagueData.France_Ligue1_URL;
            }
            else {
                item.leagueLogoURL = LeagueData.LaLiga_URL;
            }


            for(int j = 0; j < i; j++) {
                ChildItem child = new ChildItem();
                child.title = "Awesome item " + j;
                child.hint = "Too awesome";

                item.items.add(child);
            }

            items.add(item);
        }

        animatedExpListAdapter = new AnimatedExpListAdapter(this.getActivity().getApplicationContext());
        animatedExpListAdapter.setData(items);

        // get the listview
        aniExpandListView = (AnimatedExpandableListView) scheduleView.findViewById(R.id.schedule_expandableListView);
        aniExpandListView.setAdapter(animatedExpListAdapter);
        // preparing list data
        //prepareListData();

        //listAdapter = new ExpandableListAdapter(this.getActivity().getBaseContext(), listDataHeader, listDataChild);

        // setting list adapter
        //expandableListView.setAdapter(listAdapter);
        aniExpandListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (aniExpandListView.isGroupExpanded(groupPosition)) {
                    aniExpandListView.collapseGroupWithAnimation(groupPosition);
                } else {
                    aniExpandListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

        return scheduleView;
    }

    private void prepareListData() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}

/*
ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public static ScheduleFragment newInstance(int position){
        ScheduleFragment scheduleFragment = new ScheduleFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        scheduleFragment.setArguments(args);

        return scheduleFragment;
    }

    public int getPosition() {
        return getArguments().getInt(ARG_SECTION_NUMBER, defaultSectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View scheduleView = inflater.inflate(R.layout.fragment_schedule, container, false);


        // get the listview
        expandableListView = (ExpandableListView) scheduleView.findViewById(R.id.schedule_expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this.getActivity().getBaseContext(), listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(listAdapter);

        return scheduleView;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
 */