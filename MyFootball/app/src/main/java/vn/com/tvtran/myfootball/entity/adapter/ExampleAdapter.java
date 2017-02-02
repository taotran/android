package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import vn.com.tvtran.myfootball.R;
import vn.com.tvtran.myfootball.entity.Example;

/**
 * Created by tvtran on 2/2/2017.
 */

public class ExampleAdapter extends ArrayAdapter {

    private List<Example> examples;
    private LayoutInflater inflater;

    public ExampleAdapter(Context context, List<Example> examples) {
        super(context, 0, examples);
        this.examples = examples;
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
        Example example = examples.get(position);
        viewHolder.textView.setText(example.getCaption());
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
