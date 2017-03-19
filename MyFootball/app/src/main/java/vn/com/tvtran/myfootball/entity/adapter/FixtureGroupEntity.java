package vn.com.tvtran.myfootball.entity.adapter;

import android.support.annotation.NonNull;

import vn.com.tvtran.myfootball.entity.Fixture;

/**
 * Created by tvtran on 3/19/2017.
 *
 * @author tvtran
 */

public class FixtureGroupEntity implements ExpandableGroupEntity {

    private Fixture fixture;

    public FixtureGroupEntity(Fixture fixture) {
        this.fixture = fixture;
    }

    public boolean isFinished() {
        return fixture.getStatus().equals("FINISHED");
    }

    @Override
    public Integer getId() {
        return Integer.valueOf(fixture.getMatchday());
    }

    @Override
    public String getDisplayValue() {
        return "Round " + fixture.getMatchday() + "     " ;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FixtureGroupEntity))
            return false;
        FixtureGroupEntity that = (FixtureGroupEntity) obj;
        return this.getId().equals(that.getId());
    }

    @Override
    public int compareTo(@NonNull ExpandableGroupEntity o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public int hashCode() {
        return fixture.getMatchday().hashCode();
    }

}
