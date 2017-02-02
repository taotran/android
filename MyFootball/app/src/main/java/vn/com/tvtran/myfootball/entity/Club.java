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
        "links",
        "name",
        "code",
        "shortName",
        "squadMarketValue",
        "crestUrl"
})
public class Club implements Serializable
{

    @JsonProperty("clublinks")
    private ClubLinks clubLinks;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("shortName")
    private String shortName;
    @JsonProperty("squadMarketValue")
    private String squadMarketValue;
    @JsonProperty("crestUrl")
    private String crestUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    private final static long serialVersionUID = -5979173278445772111L;

    @JsonProperty("clublinks")
    public ClubLinks getClubLinks() {
        return clubLinks;
    }

    @JsonProperty("links")
    public void setClubLinks(ClubLinks clubLinks) {
        this.clubLinks = clubLinks;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("shortName")
    public String getShortName() {
        return shortName;
    }

    @JsonProperty("shortName")
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @JsonProperty("squadMarketValue")
    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    @JsonProperty("squadMarketValue")
    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    @JsonProperty("crestUrl")
    public String getCrestUrl() {
        return crestUrl;
    }

    @JsonProperty ("crestUrl")
    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
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