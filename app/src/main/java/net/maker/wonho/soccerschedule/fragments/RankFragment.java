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
public class RankFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final int defaultSectionNumber = 3;

    public static RankFragment newInstance(int posiotion) {

        RankFragment rankFragment = new RankFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, defaultSectionNumber);
        rankFragment.setArguments(args);

        return rankFragment;
    }

    public int getPosition() {

        return getArguments().getInt(ARG_SECTION_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rankView = inflater.inflate(R.layout.fragment_league_rank, container, false);

        return rankView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
