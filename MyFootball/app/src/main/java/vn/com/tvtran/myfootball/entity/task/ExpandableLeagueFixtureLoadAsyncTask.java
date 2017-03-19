package vn.com.tvtran.myfootball.entity.task;

import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import vn.com.tvtran.myfootball.entity.Fixture;
import vn.com.tvtran.myfootball.entity.adapter.ExpandableGroupEntity;
import vn.com.tvtran.myfootball.entity.adapter.FixtureGroupEntity;
import vn.com.tvtran.myfootball.entity.constant.Auth;
import vn.com.tvtran.myfootball.entity.constant.ServiceURLs;

/**
 * Created by tvtran on 3/18/2017.
 *
 * @author tvtran
 */

public class ExpandableLeagueFixtureLoadAsyncTask extends AsyncTask<String, Void, Map<ExpandableGroupEntity, List<Fixture>>> {


    @Override
    protected Map<ExpandableGroupEntity, List<Fixture>> doInBackground(String... params) {
        try {
            final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            final String requestURL = String.format(ServiceURLs.LEAGUE_FIXTURE_URL, params[0]);
            final Future<Response> response = asyncHttpClient
                    .prepareGet(requestURL)
                    .addHeader("X-Auth-Token", Auth.X_AUTH_TOKEN)
                    .execute();

            Response r = response.get();
            final StringReader reader = new StringReader(r.getResponseBody());
            final JsonReader jsonReader = new JsonReader(reader);
            final List<Fixture> fixtures = getResultFromReader(jsonReader);
            return getFixtureMap(fixtures);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

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

    protected List<JsonObject> convertJsonArrayToList(JsonArray jsonArray) {
        final List<JsonObject> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(jsonArray.get(i).getAsJsonObject());
        }
        return result;
    }

    private Map<ExpandableGroupEntity, List<Fixture>> getFixtureMap(final List<Fixture> fixtures) {
        final Map<ExpandableGroupEntity, List<Fixture>> result = new HashMap<>();
        for (Fixture fixture : fixtures) {
            final FixtureGroupEntity checkFixtureGroupEntity = new FixtureGroupEntity(fixture);
            if (result.containsKey(checkFixtureGroupEntity)) {
                result.get(checkFixtureGroupEntity).add(fixture);
            } else {
                final List<Fixture> fixtureList = new ArrayList<>();
                fixtureList.add(fixture);
                result.put(checkFixtureGroupEntity, fixtureList);

            }
        }
        return result;
    }
}
