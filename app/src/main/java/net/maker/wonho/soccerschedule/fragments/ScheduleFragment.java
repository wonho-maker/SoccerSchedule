package net.maker.wonho.soccerschedule.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.maker.wonho.soccerschedule.R;

/**
 * Created by Wonho Lee on 2015-04-08.
 */
public class ScheduleFragment extends Fragment {


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
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        return rootView;
    }
}
