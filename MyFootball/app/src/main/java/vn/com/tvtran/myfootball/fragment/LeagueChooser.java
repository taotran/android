package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.League;
import vn.com.tvtran.myfootball.entity.adapter.LeagueAdapter;
import vn.com.tvtran.myfootball.entity.task.LeagueLoadAsyncTask;

/**
 * Created by tvtran on 1/24/2017.
 */

public class LeagueChooser extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.league_chooser_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        final ListView lv = (ListView) getActivity().findViewById(R.id.league_list_view);
        new LeagueLoadAsyncTask() {
            @Override
            protected void onPostExecute(final List<League> leagues) {
                super.onPostExecute(leagues);
                lv.setAdapter(new LeagueAdapter(getActivity(), leagues));


                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final League league = leagues.get(position);
                        final LeagueTableFragment leagueTableFragmentFragment = new LeagueTableFragment();
                        final Bundle arguments = new Bundle();
                        //final String fixtureLink = league.getLinks().getFixtures().getHref();
                        //System.out.println(fixtureLink);
                        arguments.putInt("leagueId", league.getId());
                        leagueTableFragmentFragment.setArguments(arguments);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, leagueTableFragmentFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });

//                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                        final League league = leagues.get(position);
//                        final LeagueFixtureFragment leagueFixtureFragmentFragment = new LeagueFixtureFragment();
//                        final Bundle arguments = new Bundle();
//                        arguments.putInt("leagueId", league.getId());
//                        leagueFixtureFragmentFragment.setArguments(arguments);
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.fragment_container, leagueFixtureFragmentFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
//                        return true;
//                    }
//                });

                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        final League league = leagues.get(position);
                        final ExpandableFixtureFragment leagueFixtureFragmentFragment = new ExpandableFixtureFragment();
                        final Bundle arguments = new Bundle();
                        arguments.putInt("leagueId", league.getId());
                        leagueFixtureFragmentFragment.setArguments(arguments);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, leagueFixtureFragmentFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        return true;
                    }
                });

//                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                    @Override
//                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                        final League_ league = leagues.get(position);
//                        final ClubChooser clubChooserFragment = new ClubChooser();
//                        final Bundle arguments = new Bundle();
//                        arguments.putInt("leagueId", league.getId());
//                        clubChooserFragment.setArguments(arguments);
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        fragmentTransaction.replace(R.id.fragment_container, clubChooserFragment);
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();
//                        return true;
//                    }
//                });
            }
        }.execute();

    }
}
