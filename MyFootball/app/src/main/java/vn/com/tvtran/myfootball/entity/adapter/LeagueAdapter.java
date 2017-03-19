package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.League;

/**
 * Created by tvtran on 2/2/2017.
 */

public class LeagueAdapter extends ArrayAdapter {

    private List<League> leagues;
    private LayoutInflater inflater;

    public LeagueAdapter(Context context, List<League> leagues) {
        super(context, 0, leagues);
        this.leagues = leagues;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.league_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.leagueNameTextView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.leagueImageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        League league = leagues.get(position);
        viewHolder.textView.setText(league.getName());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
