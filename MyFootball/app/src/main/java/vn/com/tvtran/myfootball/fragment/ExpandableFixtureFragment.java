package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Fixture;
import vn.com.tvtran.myfootball.entity.adapter.ExpandableGroupEntity;
import vn.com.tvtran.myfootball.entity.adapter.FixtureExpandableListAdapter;
import vn.com.tvtran.myfootball.entity.adapter.FixtureGroupEntity;
import vn.com.tvtran.myfootball.entity.task.ExpandableLeagueFixtureLoadAsyncTask;

/**
 * Created by tvtran on 3/18/2017.
 *
 * @author tvtran
 */

public class ExpandableFixtureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expandable_fixture_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final ExpandableListView lv = (ExpandableListView) getActivity().findViewById(R.id.expandable_fixture_list_view);
        final Integer leagueId = getArguments().getInt("leagueId");
//        lv.setAdapter(new FixtureExpandableListAdapter(getContext(), ));
        new ExpandableLeagueFixtureLoadAsyncTask() {

            @Override
            protected void onPostExecute(Map<ExpandableGroupEntity, List<Fixture>> stringListMap) {
                super.onPostExecute(stringListMap);
                final List<ExpandableGroupEntity> groups = new ArrayList<>();
                for (ExpandableGroupEntity groupEntity : stringListMap.keySet()) {
                    groups.add(groupEntity);
                }
                Collections.sort(groups);
                lv.setAdapter(new FixtureExpandableListAdapter(getActivity(), groups, (HashMap<ExpandableGroupEntity, List<Fixture>>) stringListMap));
            }
        }.execute(leagueId.toString());
    }
}
