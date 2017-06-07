package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Fixture;
import vn.com.tvtran.myfootball.entity.adapter.FixtureAdapter;
import vn.com.tvtran.myfootball.entity.task.LeagueFixtureLoadAsyncTask;

/**
 * Created by tvtran on 2/22/2017.
 */

public class LeagueFixtureFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fixture_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView lv = (ListView) getActivity().findViewById(R.id.fixture_list_view);
        final Integer leagueId = getArguments().getInt("leagueId");
        new LeagueFixtureLoadAsyncTask() {
            @Override
            protected void onPostExecute(List<Fixture> fixtures) {
                super.onPostExecute(fixtures);
                lv.setAdapter(new FixtureAdapter(getActivity(), fixtures));
            }
        }.execute(leagueId.toString());
    }
}
