package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

/**
 * Created by tvtran on 3/5/2017.
 */

public class FixtureResult extends MFEntity {

    public FixtureResult(JsonObject object) {
        super(object);
    }

    public Integer getGoalsHomeTeam() {
        return getInteger("goalsHomeTeam");
    }

    public Integer getGoalsAwayTeam() {
        return getInteger("goalsAwayTeam");
    }
}
