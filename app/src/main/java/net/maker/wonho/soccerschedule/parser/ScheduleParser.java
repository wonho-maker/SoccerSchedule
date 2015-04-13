package net.maker.wonho.soccerschedule.parser;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.maker.wonho.soccerschedule.schedule.list.data.form.GroupItem;

import java.io.IOException;
import java.net.URL;

/**
 * Created by wonho on 2015-04-11.
 */
public class ScheduleParser {

    public GroupItem parseSchedule() throws IOException
    {
        GroupItem scheduleList = new GroupItem();

        URL naverScheduleURL = new URL("http://search.naver.com/search.naver?query=해외축구일정");

        Source source = new Source(naverScheduleURL);
        source.fullSequentialParse();

        //Element div = source

        return scheduleList;
    }
}
