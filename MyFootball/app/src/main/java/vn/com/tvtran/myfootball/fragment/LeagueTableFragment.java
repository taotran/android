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
import vn.com.tvtran.myfootball.entity.TeamStanding;
import vn.com.tvtran.myfootball.entity.adapter.LeagueTableAdapter;
import vn.com.tvtran.myfootball.entity.task.LeagueTableAsyncTask;

/**
 * Created by tvtran on 2/3/2017.
 */

public class LeagueTableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_table_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView lv = (ListView) getActivity().findViewById(R.id.league_table_list_view);
        final Integer leagueId = getArguments().getInt("leagueId");
        new LeagueTableAsyncTask() {
            @Override
            protected void onPostExecute(List<TeamStanding> teamStandings) {
                super.onPostExecute(teamStandings);
                lv.setAdapter(new LeagueTableAdapter(getActivity(), teamStandings));
            }
        }.execute(leagueId.toString());
    }
}
