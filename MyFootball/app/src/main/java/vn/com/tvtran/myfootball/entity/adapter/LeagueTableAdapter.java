package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.TeamStanding;

/**
 * Created by tvtran on 2/3/2017.
 */

public class LeagueTableAdapter extends AbstractArrayAdapter<TeamStanding> {


    public LeagueTableAdapter(Context context, List<TeamStanding> objects) {
        super(context, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.league_table_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.position = (TextView) convertView.findViewById(R.id.positionTextView);
            viewHolder.clubName = (TextView) convertView.findViewById(R.id.clubName1TextView);
            viewHolder.numberOfPlayedGames = (TextView) convertView.findViewById(R.id.numberOfPlayedGamesTextView);
            viewHolder.clubPoints = (TextView) convertView.findViewById(R.id.clubPointsTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final TeamStanding teamStanding = objects.get(position);
        viewHolder.position.setText(String.valueOf(teamStanding.getPosition()));
        viewHolder.clubName.setText(teamStanding.getTeamName() + "  ");
        viewHolder.numberOfPlayedGames.setText(String.valueOf(teamStanding.getPlayedGames()));
        viewHolder.clubPoints.setText(String.valueOf(teamStanding.getPoints()));
        return convertView;
    }

    static class ViewHolder {
        TextView position;
        TextView clubName;
        TextView numberOfPlayedGames;
        TextView clubPoints;
    }
}
