package net.maker.wonho.soccerschedule.schedule.list.data.form;

import net.maker.wonho.soccerschedule.schedule.list.data.LeagueData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wonho Lee on 2015-04-09.
 */
public class GroupItem {

    public GroupItem() {

    }
    public GroupItem(String leagueTitle) {
        title = leagueTitle;

        leagueLogoURL = LeagueData.leageEmblemURL(title);

    }

    public String title;
    public List<ChildItem> items = new ArrayList<ChildItem>();
    public String leagueLogoURL;
}

