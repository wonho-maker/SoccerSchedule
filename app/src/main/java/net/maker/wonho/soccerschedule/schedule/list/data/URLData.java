package net.maker.wonho.soccerschedule.schedule.list.data;

import net.maker.wonho.soccerschedule.parser.ParserData;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wonho on 2015-04-13.
 */
public class URLData {

    private String naverSearchURL = "http://search.naver.com/search.naver?query=";

    private String queryForSchedule = "해외축구일정";

    public URL naverScheduleURL;

    public URLData() {
        try {
            naverScheduleURL = new URL(naverSearchURL + URLEncoder.encode(queryForSchedule, "UTF-8"));
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
    }

    public URL getURL(ParserData.ParseType parseType) {
        if(parseType == ParserData.ParseType.SCHEDULE) {
            return naverScheduleURL;
        }
        else if(parseType == ParserData.ParseType.NEWS) {
            return null;
        }
        else if(parseType == ParserData.ParseType.RANK) {
            return null;
        }
        else {

        }

        return null;
    }

    private URL getNaverScheduleURL() {
        return naverScheduleURL;
    }
}
