package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Fixture;

/**
 * Created by tvtran on 2/21/2017.
 */

public class FixtureAdapter extends AbstractArrayAdapter<Fixture> {

    public FixtureAdapter(Context context, List<Fixture> objects) {
        super(context, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fixture_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.homeTeamNameTextView = (TextView) convertView.findViewById(R.id.homeTeamNameTextView);
            viewHolder.homeTeamGoalsTextView = (TextView) convertView.findViewById(R.id.homeTeamGoalsTextView);
            viewHolder.awayTeamGoalsTextView = (TextView) convertView.findViewById(R.id.awayTeamGoalsTextView);
            viewHolder.awayTeamNameTextView = (TextView) convertView.findViewById(R.id.awayTeamNameTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Fixture fixture = objects.get(position);
        final Integer goalsHomeTeam = fixture.getResult().getGoalsHomeTeam();
        final Integer goalsAwayTeam = fixture.getResult().getGoalsAwayTeam();
        viewHolder.homeTeamNameTextView.setText(fixture.getHomeTeamName());
        viewHolder.homeTeamGoalsTextView.setText(goalsHomeTeam != null ? String.valueOf(goalsHomeTeam) : "--");
        viewHolder.homeTeamGoalsTextView.setEnabled(false);
        viewHolder.awayTeamGoalsTextView.setText(goalsAwayTeam != null ? String.valueOf(goalsAwayTeam) : "--");
        viewHolder.awayTeamGoalsTextView.setEnabled(false);
        viewHolder.awayTeamNameTextView.setText(fixture.getAwayTeamName());

        return convertView;
    }

    static class ViewHolder {
        TextView homeTeamNameTextView;
        TextView homeTeamGoalsTextView;
        TextView awayTeamGoalsTextView;
        TextView awayTeamNameTextView;
    }
}
