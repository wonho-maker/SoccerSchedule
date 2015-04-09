package net.maker.wonho.soccerschedule.schedule.list.view;

import android.content.Context;
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
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_item_textView);
            //holder.hint = (TextView) convertView.findViewById(R.id.textHint);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.title.setText(item.title);
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
            holder.title = (TextView) convertView.findViewById(R.id.schedule_list_group_textView);
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

