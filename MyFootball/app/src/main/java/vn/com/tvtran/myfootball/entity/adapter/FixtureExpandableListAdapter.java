package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Fixture;

/**
 * Created by tvtran on 3/18/2017.
 *
 * @author tvtran
 */

public class FixtureExpandableListAdapter extends AbstractExpandableListAdapter<Fixture, ExpandableGroupEntity> {

    public FixtureExpandableListAdapter(Context context, List<ExpandableGroupEntity> listDataHeader, HashMap<ExpandableGroupEntity, List<Fixture>> _listDataChild) {
        super(context, listDataHeader, _listDataChild);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final FixtureGroupEntity round = (FixtureGroupEntity) getGroup(groupPosition);
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fixture_round_group, null);
        }
        final TextView roundTextView = (TextView) convertView.findViewById(R.id.fixtureRoundTextView);
        roundTextView.setText(round.getDisplayValue());
        if (round.isFinished()) {
//            roundTextView.setBackgroundColor(Color.GRAY);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Fixture fixture = (Fixture) getChild(groupPosition, childPosition);
        ViewHolder viewHolder;
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fixture_round_group_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.homeTeamNameTextView = (TextView) convertView.findViewById(R.id.homeTeamNameTextView);
            viewHolder.homeTeamGoalsTextView = (TextView) convertView.findViewById(R.id.homeTeamGoalsTextView);
            viewHolder.awayTeamGoalsTextView = (TextView) convertView.findViewById(R.id.awayTeamGoalsTextView);
            viewHolder.awayTeamNameTextView = (TextView) convertView.findViewById(R.id.awayTeamNameTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Integer goalsHomeTeam = fixture.getResult().getGoalsHomeTeam();
        final Integer goalsAwayTeam = fixture.getResult().getGoalsAwayTeam();
        viewHolder.homeTeamNameTextView.setText(fixture.getHomeTeamName());

        viewHolder.homeTeamGoalsTextView.setText(goalsHomeTeam != null ? String.valueOf(goalsHomeTeam) : "--");
        viewHolder.homeTeamGoalsTextView.setTextColor(Color.RED);
        viewHolder.homeTeamGoalsTextView.setEnabled(false);

        viewHolder.awayTeamGoalsTextView.setText(goalsAwayTeam != null ? String.valueOf(goalsAwayTeam) : "--");
        viewHolder.awayTeamGoalsTextView.setTextColor(Color.RED);
        viewHolder.awayTeamGoalsTextView.setEnabled(false);


        viewHolder.awayTeamNameTextView.setText(fixture.getAwayTeamName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ViewHolder {
        TextView homeTeamNameTextView;
        TextView homeTeamGoalsTextView;
        TextView awayTeamGoalsTextView;
        TextView awayTeamNameTextView;
    }
}
