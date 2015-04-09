package net.maker.wonho.soccerschedule.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.maker.wonho.soccerschedule.MainActivity;
import net.maker.wonho.soccerschedule.R;

/**
 * Created by Wonho Lee on 2015-04-08.
 */
public class ScheduleFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final int defaultSectionNumber = 1;

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

        return scheduleView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
