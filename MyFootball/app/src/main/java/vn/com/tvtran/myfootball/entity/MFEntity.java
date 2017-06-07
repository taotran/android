package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

/**
 * Created by tvtran on 3/4/2017.
 */

public class MFEntity implements IMFEntity {

    private JsonObject object;

    public MFEntity(JsonObject object) {
        this.object = object;
    }

    @Override
    public Integer getId() {
        return null;
    }

    public String getString(String jsonProperty) {
        return object.get(jsonProperty).getAsString();
    }

    public Integer getInteger(String jsonProperty) {
        return object.get(jsonProperty).isJsonNull() ? null : object.get(jsonProperty).getAsInt();
    }

    public Boolean getBoolean(String jsonProperty) {
        return object.get(jsonProperty).getAsBoolean();
    }

    public JsonObject getObject(String jsonProperty) {
        return object.getAsJsonObject(jsonProperty);
    }
}
