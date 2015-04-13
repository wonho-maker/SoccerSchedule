package net.maker.wonho.soccerschedule.schedule.list.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idunnololz.widgets.AnimatedExpandableListView;

import net.maker.wonho.soccerschedule.R;
import net.maker.wonho.soccerschedule.schedule.list.data.form.ChildHolder;
import net.maker.wonho.soccerschedule.schedule.list.data.form.ChildItem;
import net.maker.wonho.soccerschedule.schedule.list.data.form.GroupHolder;
import net.maker.wonho.soccerschedule.schedule.list.data.form.GroupItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Wonho Lee on 2015-04-09.
 */
public class AnimatedExpListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private LayoutInflater inflater;

    private List<GroupItem> items;

    public AnimatedExpListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<GroupItem> items) {
        this.items = items;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).items.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        ChildItem item = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = inflater.inflate(R.layout.schedule_list_item, parent, false);

            holder.timeView = (TextView) convertView.findViewById(R.id.schedule_list_item_time_textView);

            holder.homeTeamTitleView = (TextView) convertView.findViewById(R.id.schedule_list_item_homeTeam_textView);
            holder.homeTeamEmblem = (ImageView) convertView.findViewById(R.id.schedule_list_item_homeTeam_imageView);

            holder.scoreView = (TextView) convertView.findViewById(R.id.schedule_list_item_score_textView);

            holder.awayTeamTitleView = (TextView) convertView.findViewById(R.id.schedule_list_item_awayTeam_textView);
            holder.awayTeamEmblem = (ImageView) convertView.findViewById(R.id.schedule_list_item_awayTeam_imageView);

            holder.placeView = (TextView) convertView.findViewById(R.id.schedule_list_item_place_textView);

            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.timeView.setText(item.time);


        holder.homeTeamTitleView.setText(item.homeTeamTitle);
        Glide.with(convertView.getContext()).load(item.homeTeamEmblemURL).into(holder.homeTeamEmblem);

        //highlight the winner's score
        if(item.score.contains(":")) {
            SpannableStringBuilder scoreBuilder = new SpannableStringBuilder(item.score);

            int splitIndex = item.score.indexOf(":");

            int homeScore = Integer.parseInt(item.score.split(":")[0].trim());
            int awayScore = Integer.parseInt(item.score.split(":")[1].trim());

            if(homeScore > awayScore) {

                scoreBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0, splitIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.scoreView.setText(scoreBuilder);

            }
            else if(homeScore < awayScore) {

                scoreBuilder.setSpan(new ForegroundColorSpan(Color.RED), splitIndex, scoreBuilder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.scoreView.setText(scoreBuilder);

            }
            else {

                holder.scoreView.setText(item.score);
            }

        }
        else {
            holder.scoreView.setText(item.score);
        }


        holder.awayTeamTitleView.setText(item.awayTeamTitle);
        Glide.with(convertView.getContext()).load(item.awayTeamEmblemURL).into(holder.awayTeamEmblem);

        holder.placeView.setText(item.place);
//        holder.hint.setText(item.hint);

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return items.get(groupPosition).items.size();
    }

    @Override
    public GroupItem getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        GroupItem item = getGroup(groupPosition);
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(R.layout.schedule_list_group, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_group_title_textView);
            holder.leagueLogo = (ImageView) convertView.findViewById(R.id.schedule_list_group_logo_imageView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        holder.title.setText(item.title);
        Glide.with(convertView.getContext()).load(item.leagueLogoURL).into(holder.leagueLogo);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }


}

