package net.maker.wonho.soccerschedule.parser;

import android.os.AsyncTask;
import android.util.Log;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.maker.wonho.soccerschedule.schedule.list.data.URLData;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by wonho on 2015-04-13.
 */

 public abstract class ParseTask extends AsyncTask<ParserData.ParseType, Integer, List<Element>> {

    @Override
    abstract protected void onPreExecute();


    @Override
    protected List<Element> doInBackground(ParserData.ParseType... types) {
        URLData urlData = new URLData();
        URL url = urlData.getURL(types[0]);

        Log.i("url", url.toString());
        Source source = null;

        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla");
            source = new Source(urlConnection);

        } catch (IOException e) {
            e.printStackTrace();
            Log.i("taskBackground", "1");
        }

        if (source != null) {

            Log.i("source2", source.getPreliminaryEncodingInfo());
            //Log.i("source3", source.getRenderer().toString());

        } else {
            Log.i("source", "null");
        }

        //second table is shedule table.
        Element element = source.getAllElements(HTMLElementName.TABLE).get(1);

        //extract each schedule
        List<Element> scheduleTable = element.getAllElements(HTMLElementName.TD);

        for (Element tableData : scheduleTable) {

            //match time and league data    example)<td class=time> ... </td>
            if (tableData.getAttributeValue("class").equals("time")) {

                //parse match time    example) <span class="bg_none">01:00</span>
                tableData.getFirstElement("span").getTextExtractor().toString();

                //parse league   example) <p title="프리메라리가">
                tableData.getFirstElement("p").getAttributeValue("title");
            }

            //home team data example) <td class="l_team"> ... </td>
            if (tableData.getAttributeValue("class").equals("l_team")) {

                // example)  <img src="image adress" width="36" height="36" alt="말라가 CF">
                //team name - alt attribute value
                tableData.getFirstElement("img").getAttributeValue("alt");

                //team emblem - img src
                tableData.getFirstElement("img").getAttributeValue("src");
            }

            /*   <td class="score"> ... </td>
                 <td class="score"> VS </td>    - before start match
                 <td class="score"> 2:2 </td>   - after match
             */
            if (tableData.getAttributeValue("class").equals("score")) {
                tableData.getTextExtractor().toString();
            }

            //away team data example) <td class="l_team"> ... </td>
            if (tableData.getAttributeValue("class").equals("r_team")) {

                // example)  <img src="image adress" width="36" height="36" alt="레알 마드리드">
                //team name - alt attribute value
                tableData.getFirstElement("img").getAttributeValue("alt");

                //team emblem - img src
                tableData.getFirstElement("img").getAttributeValue("src");
            }

            //place -  example)  <td class="place" title="La Rosaleda">La Rosaleda</td>
            if (tableData.getAttributeValue("class").equals("place")) {
                tableData.getAttributeValue("title");
            }

        }

        return scheduleTable;
    }

    @Override
    abstract protected void onPostExecute(List<Element> source);

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
