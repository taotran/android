package vn.com.tvtran.myfootball.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "self",
        "teams",
        "fixtures",
        "leagueTable"
})
public class Links  implements Serializable {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("teams")
    private Teams teams;
    @JsonProperty("fixtures")
    private Fixtures fixtures;
    @JsonProperty("leagueTable")
    private LeagueTable leagueTable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("teams")
    public Teams getTeams() {
        return teams;
    }

    @JsonProperty("teams")
    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    @JsonProperty("fixtures")
    public Fixtures getFixtures() {
        return fixtures;
    }

    @JsonProperty("fixtures")
    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    @JsonProperty("leagueTable")
    public LeagueTable getLeagueTable() {
        return leagueTable;
    }

    @JsonProperty("leagueTable")
    public void setLeagueTable(LeagueTable leagueTable) {
        this.leagueTable = leagueTable;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}