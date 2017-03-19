package vn.com.tvtran.myfootball.entity.task;

import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import vn.com.tvtran.myfootball.entity.IMFEntity;
import vn.com.tvtran.myfootball.entity.constant.Auth;

/**
 * Created by tvtran on 3/5/2017.
 */

public abstract class MFAsyncTask<Params, Progress, Result extends List<? extends IMFEntity>> extends AsyncTask<Params, Progress, Result> {

    private String requestURL;

    public MFAsyncTask(String requestURL) {
        this.requestURL = requestURL;
    }

    @Override
    protected Result doInBackground(Params... params) {
        try {
            final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

            final Future<Response> response = asyncHttpClient
                    .prepareGet(getUrl(params))
                    .addHeader("X-Auth-Token", Auth.X_AUTH_TOKEN)
                    .execute();

            Response r = response.get();
            final StringReader reader = new StringReader(r.getResponseBody());
            final JsonReader jsonReader = new JsonReader(reader);
            return getResultFromReader(jsonReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEmptyResult();
    }

    protected abstract Result getResultFromReader(JsonReader reader);

    protected abstract Result getEmptyResult();

    protected String getUrl(Params... params) {
        if (params == null)
            return requestURL;
        return requestURL;
    }

    protected List<JsonObject> convertJsonArrayToList(JsonArray jsonArray) {
        final List<JsonObject> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(jsonArray.get(i).getAsJsonObject());
        }
        return result;
    }

    public String getRequestURL() {
        return requestURL;
    }
}
