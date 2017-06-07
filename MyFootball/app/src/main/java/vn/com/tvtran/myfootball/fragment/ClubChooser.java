package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.com.tvtran.myfootball.R;

/**
 * Created by tvtran on 2/2/2017.
 */

public class ClubChooser extends Fragment {

    private static final String URL = "http://api.football-data.org/v1/soccerseasons/426/teams";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.club_chooser_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView lv = (ListView) getActivity().findViewById(R.id.club_list_view);
        final Integer leagueId = getArguments().getInt("leagueId");
//        new ClubLoadAsyncTask() {
//            @Override
//            protected void onPostExecute(List<Club> clubs) {
//                super.onPostExecute(clubs);
//                lv.setAdapter(new ClubAdapter(getActivity(), clubs));
//
//                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    }
//                });
//            }
//        }.execute(leagueId.toString());

    }
}
