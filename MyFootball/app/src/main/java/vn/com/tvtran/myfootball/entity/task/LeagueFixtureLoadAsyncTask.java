package vn.com.tvtran.myfootball.entity.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.util.ArrayList;
import java.util.List;

import vn.com.tvtran.myfootball.entity.Fixture;
import vn.com.tvtran.myfootball.entity.constant.ServiceURLs;

/**
 * Created by tvtran on 2/22/2017.
 */

public class LeagueFixtureLoadAsyncTask extends MFAsyncTask<String, Void, List<Fixture>> {


    public LeagueFixtureLoadAsyncTask() {
        super(ServiceURLs.LEAGUE_FIXTURE_URL);
    }

    @Override
    protected List<Fixture> getResultFromReader(JsonReader reader) {
        JsonParser parser = new JsonParser();
        final JsonObject responseObject = (JsonObject) parser.parse(reader);
        final List<JsonObject> fixtureJsonObjects = convertJsonArrayToList(responseObject.getAsJsonArray("fixtures"));

        final List<Fixture> fixtures = new ArrayList<>();
        for (JsonObject fixture : fixtureJsonObjects) {
            fixtures.add(new Fixture(fixture));
        }

        return fixtures;
    }

    @Override
    protected List<Fixture> getEmptyResult() {
        return new ArrayList<>();
    }

    @Override
    protected String getUrl(String... params) {
        return String.format(getRequestURL(), params[0]);
    }
}
