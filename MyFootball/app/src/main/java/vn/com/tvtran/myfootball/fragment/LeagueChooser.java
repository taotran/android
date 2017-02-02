package vn.com.tvtran.myfootball.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import vn.com.tvtran.myfootball.entity.Example;
import vn.com.tvtran.myfootball.entity.adapter.ExampleAdapter;
import vn.com.tvtran.myfootball.entity.task.LeagueLoadAsyncTask;

/**
 * Created by tvtran on 1/24/2017.
 */

public class LeagueChooser extends Fragment {
    private static final String URL = "http://football-data.org/v1/soccerseasons";

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
            protected void onPostExecute(final List<Example> examples) {
                super.onPostExecute(examples);
                lv.setAdapter(new ExampleAdapter(getActivity(), examples));

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Example example = examples.get(position);
                        System.out.println(example);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new ClubChooser());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });
            }
        }.execute();

    }
}
