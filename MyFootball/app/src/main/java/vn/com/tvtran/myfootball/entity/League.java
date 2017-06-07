package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

/**
 * Created by tvtran on 3/4/2017.
 */

public class League extends MFEntity {
    public League(JsonObject object) {
        super(object);
    }

    public Integer getId() {
        return getInteger("id");
    }

    public String getName() {
        return getString("caption");
    }

    public String getYear() {
        return getString("year");
    }

    public Integer getCurrentMatchday() {
        return getInteger("currentMatchday");
    }

    public Integer getNumberOfMatchdays() {
        return getInteger("numberOfMatchdays");
    }

    public Integer getNumberOfTeams() {
        return getInteger("numberOfTeams");
    }

    public Integer getNumberOfGames() {
        return getInteger("numberOfGames");
    }
}
