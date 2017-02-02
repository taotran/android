package vn.com.tvtran.myfootball.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tvtran on 2/2/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "self",
        "fixtures",
        "players"
})
public class ClubLinks implements Serializable
{

    @JsonProperty("self")
    private Self self;
    @JsonProperty("fixtures")
    private Fixtures fixtures;
    @JsonProperty("players")
    private Players players;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -9000104193218805196L;

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("fixtures")
    public Fixtures getFixtures() {
        return fixtures;
    }

    @JsonProperty("fixtures")
    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    @JsonProperty("players")
    public Players getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(Players players) {
        this.players = players;
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
