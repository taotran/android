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
        "links",
        "id",
        "caption",
        "league",
        "year",
        "currentMatchday",
        "numberOfMatchdays",
        "numberOfTeams",
        "numberOfGames",
        "lastUpdated"
})
public class Example implements Serializable{

    @JsonProperty("links")
    private Links links;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("caption")
    private String caption;
    @JsonProperty("league")
    private String league;
    @JsonProperty("year")
    private String year;
    @JsonProperty("currentMatchday")
    private Integer currentMatchday;
    @JsonProperty("numberOfMatchdays")
    private Integer numberOfMatchdays;
    @JsonProperty("numberOfTeams")
    private Integer numberOfTeams;
    @JsonProperty("numberOfGames")
    private Integer numberOfGames;
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "Example{" +
                "links=" + links +
                ", id=" + id +
                ", caption='" + caption + '\'' +
                ", league='" + league + '\'' +
                ", year='" + year + '\'' +
                ", currentMatchday=" + currentMatchday +
                ", numberOfMatchdays=" + numberOfMatchdays +
                ", numberOfTeams=" + numberOfTeams +
                ", numberOfGames=" + numberOfGames +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @JsonProperty("links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    @JsonProperty("caption")
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @JsonProperty("league")
    public String getLeague() {
        return league;
    }

    @JsonProperty("league")
    public void setLeague(String league) {
        this.league = league;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("currentMatchday")
    public Integer getCurrentMatchday() {
        return currentMatchday;
    }

    @JsonProperty("currentMatchday")
    public void setCurrentMatchday(Integer currentMatchday) {
        this.currentMatchday = currentMatchday;
    }

    @JsonProperty("numberOfMatchdays")
    public Integer getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    @JsonProperty("numberOfMatchdays")
    public void setNumberOfMatchdays(Integer numberOfMatchdays) {
        this.numberOfMatchdays = numberOfMatchdays;
    }

    @JsonProperty("numberOfTeams")
    public Integer getNumberOfTeams() {
        return numberOfTeams;
    }

    @JsonProperty("numberOfTeams")
    public void setNumberOfTeams(Integer numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    @JsonProperty("numberOfGames")
    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    @JsonProperty("numberOfGames")
    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    @JsonProperty("lastUpdated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("lastUpdated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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
