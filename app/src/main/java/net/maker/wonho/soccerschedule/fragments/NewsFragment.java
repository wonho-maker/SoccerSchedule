package net.maker.wonho.soccerschedule.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.maker.wonho.soccerschedule.MainActivity;
import net.maker.wonho.soccerschedule.R;

/**
 * Created by wonho on 2015-04-10.
 */
public class NewsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final int defaultSectionNumber = 2;

    public static NewsFragment newInstance(int position){

        NewsFragment newsFragment = new NewsFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        newsFragment.setArguments(args);

        return newsFragment;
    }

    public int getPostion() {
        return getArguments().getInt(ARG_SECTION_NUMBER, defaultSectionNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);

        return newsView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}


/*
public static ScheduleFragment newInstance(int position){
        ScheduleFragment scheduleFragment = new ScheduleFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        scheduleFragment.setArguments(args);

        return scheduleFragment;
    }

    public int getPosition() {
        return getArguments().getInt("position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View scheduleView = inflater.inflate(R.layout.fragment_schedule, container, false);

        return scheduleView;
    }
 */