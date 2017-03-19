package vn.com.tvtran.myfootball.entity.adapter;

import android.support.annotation.NonNull;

/**
 * Created by tvtran on 3/19/2017.
 *
 * @author tvtran
 */

public interface ExpandableGroupEntity extends Comparable<ExpandableGroupEntity> {

    Integer getId();

    String getDisplayValue();
}
