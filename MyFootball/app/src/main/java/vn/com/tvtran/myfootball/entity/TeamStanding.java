package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

/**
 * Created by tvtran on 3/5/2017.
 */

public class TeamStanding extends MFEntity {

    public TeamStanding(JsonObject object) {
        super(object);
    }

    public Integer getPosition() {
        return getInteger("position");
    }

    public String getTeamName() {
        return getString("teamName");
    }

    public String getTeamImgLink() {
        return getString("crestURI");
    }

    public Integer getPlayedGames() {
        return getInteger("playedGames");
    }

    public Integer getPoints() {
        return getInteger("points");
    }

    public Integer getGoals() {
        return getInteger("goals");
    }

    public Integer getGoalsAgainst() {
        return getInteger("goalsAgainst");
    }

    public Integer getGoalsDifference() {
        return getInteger("goalsDifference");
    }

    public Integer getWins() {
        return getInteger("wins");
    }

    public Integer getDraws() {
        return getInteger("draws");
    }

    public Integer getLosses() {
        return getInteger("losses");
    }
}
