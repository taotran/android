package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.util.List;
import java.util.concurrent.Future;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Club;
import vn.com.tvtran.myfootball.entity.adapter.ClubAdapter;
import vn.com.tvtran.myfootball.entity.task.ClubLoadAsyncTask;

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
        new ClubLoadAsyncTask() {
            @Override
            protected void onPostExecute(List<Club> clubs) {
                super.onPostExecute(clubs);
                lv.setAdapter(new ClubAdapter(getActivity(), clubs));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Example example = clubs.get(position);
//                    System.out.println(example);
//                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                    fragmentTransaction.add(R.id.fragment_container, new ClubChooser());
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
                    }
                });
            }
        }.execute("426");

    }
}
