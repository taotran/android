package vn.com.tvtran.myfootball.entity;

import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by tvtran on 3/5/2017.
 */

public interface IMFEntity extends Serializable {

    Integer getId();

    String getString(String jsonProperty);

    Integer getInteger(String jsonProperty);

    Boolean getBoolean(String jsonProperty);

    JsonObject getObject(String jsonProperty);

}
