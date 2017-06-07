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
        "goalsHomeTeam",
        "goalsAwayTeam"
})
public class Result implements Serializable
{

    @JsonProperty("goalsHomeTeam")
    private Integer goalsHomeTeam;
    @JsonProperty("goalsAwayTeam")
    private Integer goalsAwayTeam;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -558070286838417836L;

    @JsonProperty("goalsHomeTeam")
    public Integer getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    @JsonProperty("goalsHomeTeam")
    public void setGoalsHomeTeam(Integer goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    @JsonProperty("goalsAwayTeam")
    public Integer getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    @JsonProperty("goalsAwayTeam")
    public void setGoalsAwayTeam(Integer goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
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