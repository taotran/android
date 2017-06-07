package vn.com.tvtran.myfootball.entity.task;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.ArrayList;
import java.util.List;

import vn.com.tvtran.myfootball.entity.League;
import vn.com.tvtran.myfootball.entity.constant.ServiceURLs;

/**
 * Created by tvtran on 2/2/2017.
 */

public class LeagueLoadAsyncTask extends MFAsyncTask<Void, Void, List<League>> {

    protected LeagueLoadAsyncTask() {
        super(ServiceURLs.LEAGUES_URL);
    }

    @Override
    protected List<League> doInBackground(Void... params) {
        return super.doInBackground(params);
    }

    @Override
    protected List<League> getResultFromReader(JsonReader reader) {
        final JsonParser parser = new JsonParser();
        final List<JsonObject> jsonLeagues = convertJsonArrayToList((JsonArray) parser
                .parse(reader));
        final List<League> leagues = new ArrayList<>();
        for (JsonObject object : jsonLeagues) {
            leagues.add(new League(object));
        }
        return leagues;
    }

    @Override
    protected List<League> getEmptyResult() {
        return new ArrayList<>();
    }


}
