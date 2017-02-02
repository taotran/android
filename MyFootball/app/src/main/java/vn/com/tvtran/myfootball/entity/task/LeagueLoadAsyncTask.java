package vn.com.tvtran.myfootball.entity.task;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import vn.com.tvtran.myfootball.entity.Example;

/**
 * Created by tvtran on 2/2/2017.
 */

public class LeagueLoadAsyncTask extends AsyncTask<Void, Void, List<Example>> {
    private static final String URL = "http://api.football-data.org/v1/soccerseasons";

    @Override
    protected List<Example> doInBackground(Void... params) {
        try {

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            Future<Response> response = asyncHttpClient.prepareGet(URL).addHeader("X-Auth-Token", "6ac45b6fb72643498e9d869610436b8d").execute();

            Response r = response.get();
            String formattedResult = r.getResponseBody().replace("_links", "links");
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final List<Example> examples = mapper.readValue(formattedResult, new TypeReference<List<Example>>() {
            });
            System.out.println(examples.get(1).getCaption());
            System.out.println(examples.get(1).getNumberOfTeams());
            return examples;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
