package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Club;

/**
 * Created by tvtran on 2/2/2017.
 */

public class ClubAdapter extends ArrayAdapter {

    private List<Club> clubs;
    private LayoutInflater inflater;

    public ClubAdapter(Context context, List<Club> clubs) {
        super(context, 0, clubs);
        this.clubs = clubs;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClubAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.club_list_item, parent, false);
            viewHolder = new ClubAdapter.ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.clubNameTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ClubAdapter.ViewHolder)convertView.getTag();
        }
        Club club = clubs.get(position);
        viewHolder.textView.setText(club.getName());
        return convertView;
    }

    static class ViewHolder {

        TextView textView;
    }
}
