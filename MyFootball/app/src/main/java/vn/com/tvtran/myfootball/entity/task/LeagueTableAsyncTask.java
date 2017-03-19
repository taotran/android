package vn.com.tvtran.myfootball.entity.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.ArrayList;
import java.util.List;

import vn.com.tvtran.myfootball.entity.TeamStanding;
import vn.com.tvtran.myfootball.entity.constant.ServiceURLs;

/**
 * Created by tvtran on 2/3/2017.
 */

public class LeagueTableAsyncTask extends MFAsyncTask<String, Void, List<TeamStanding>> {

    public LeagueTableAsyncTask() {
        super(ServiceURLs.LEAGUE_TABLE_URL);
    }

    @Override
    protected List<TeamStanding> doInBackground(String... params) {
        return super.doInBackground(params);
    }

    @Override
    protected List<TeamStanding> getResultFromReader(JsonReader reader) {
        final JsonParser parser = new JsonParser();
        final JsonObject responseObject = (JsonObject) parser.parse(reader);
        final List<JsonObject> jsonLeagues = convertJsonArrayToList(responseObject.getAsJsonArray("standing"));
        final List<TeamStanding> teamStandings = new ArrayList<>();
        for (JsonObject object : jsonLeagues) {
            teamStandings.add(new TeamStanding(object));
        }
        return teamStandings;
    }

    @Override
    protected List<TeamStanding> getEmptyResult() {
        return new ArrayList<>();
    }

    @Override
    protected String getUrl(String... params) {
        return String.format(getRequestURL(), params[0]);
    }
}
