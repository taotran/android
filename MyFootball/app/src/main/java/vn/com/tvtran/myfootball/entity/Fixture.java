package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

/**
 * Created by tvtran on 3/5/2017.
 */

public class Fixture extends MFEntity {

    public Fixture(JsonObject object) {
        super(object);
    }

    public Integer getId() {
        final String href = getSelf().get("href").getAsString();
        //http://api.football-data.org/v1/fixtures/150841
        final String[] parseHref = href.split("/");
        return Integer.valueOf(parseHref[parseHref.length - 1]);
    }

    public String getMatchday() {
        return getString("matchday");
    }

    public String getHomeTeamName() {
        return getString("homeTeamName");
    }

    public String getAwayTeamName() {
        return getString("awayTeamName");
    }

    public FixtureResult getResult() {
        return new FixtureResult(getObject("result"));
    }

    public String getDate() {
        return getString("date");
    }

    public String getStatus() {
        return getString("status");
    }

    public JsonObject getLinks() {
        return getObject("_links");
    }

    public JsonObject getSelf() {
        return getLinks().getAsJsonObject("self");
    }

}
