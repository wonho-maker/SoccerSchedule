package net.maker.wonho.soccerschedule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.htmlparser.jericho.Element;
import net.maker.wonho.soccerschedule.R;
import net.maker.wonho.soccerschedule.fragments.NewsFragment;
import net.maker.wonho.soccerschedule.fragments.RankFragment;
import net.maker.wonho.soccerschedule.fragments.ScheduleFragment;
import net.maker.wonho.soccerschedule.parser.ParseTask;
import net.maker.wonho.soccerschedule.parser.ParserData;
import net.maker.wonho.soccerschedule.schedule.list.data.form.GroupItem;

import java.util.List;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    ProgressDialog taskProgressDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskProgressDia = new ProgressDialog(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawerLayout);




    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        position += 1;
        Log.i("position : ", " "+position);

        switch(position) {
            case 1:

                ScheduleFragment scheduleFragment = (ScheduleFragment) fragmentManager.findFragmentByTag("scheduleFragment");
                Log.i("fragments", fragmentManager.getFragments().toString());

                if (scheduleFragment == null) {
                    Log.i("test", "position");
                    scheduleFragment = scheduleFragment.newInstance(position);

                    fragmentManager.beginTransaction()
                            .replace(R.id.container, scheduleFragment, "scheduleFragment")
                            .commit();
                } else if (scheduleFragment.getPosition() != position) {
                    scheduleFragment = scheduleFragment.newInstance(position);
                    Log.i("test", "position2");
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, scheduleFragment, "scheduleFragment")
                            .commit();
                }



                new ScheduleParseTask().execute(ParserData.ParseType.SCHEDULE);

                break;
            case 2:
                NewsFragment newsFragment = (NewsFragment) fragmentManager.findFragmentByTag("newsFragment");

                if(newsFragment == null || newsFragment.getPostion() != position) {
                    newsFragment = newsFragment.newInstance(position);

                }

                fragmentManager.beginTransaction()
                        .replace(R.id.container, newsFragment, "newsFragment")
                        .commit();
                break;
            case 3:
                RankFragment rankFragment = (RankFragment) fragmentManager.findFragmentByTag("rankFragment");

                if(rankFragment == null || rankFragment.getPosition() != position) {
                    rankFragment = rankFragment.newInstance(position);

                }

                fragmentManager.beginTransaction()
                        .replace(R.id.container, rankFragment, "rankFragment")
                        .commit();
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ScheduleParseTask extends ParseTask {
        @Override
        protected void onPreExecute() {
            taskProgressDia.setTitle("Schedule Loading...");
            taskProgressDia.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            taskProgressDia.setCancelable(false);

            taskProgressDia.show();

            Log.i("test", "test");
        }

        @Override
        protected void onPostExecute(List<GroupItem> sheduleListData) {

            /*TextView textView = (TextView) findViewById(R.id.textView1);
            textView.setText(" ");
            for (int i =0; i < source.size(); i++) {
                textView.setText(" ");
                textView.append("number "+i+"\n"+source.get(i).toString());
            }*/
            FragmentManager fragmentManager = getSupportFragmentManager();

            ScheduleFragment scheduleFragment = (ScheduleFragment) fragmentManager.findFragmentByTag("scheduleFragment");

            //Log.i("test", fragmentManager.getFragments().toString());
            if(scheduleFragment != null) {
                scheduleFragment.updateList(sheduleListData);

            }else{
                //Log.i("main", "fragment null");
            }

            /*fragmentManager.beginTransaction()
                    .replace(R.id.container, scheduleFragment)
                    .commit();*/


            taskProgressDia.dismiss();
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    //public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        //private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        /*public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }*/

}
