package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
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
        final ClubAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.club_list_item, parent, false);
            viewHolder = new ClubAdapter.ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.clubNameTextView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.clubImageView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ClubAdapter.ViewHolder) convertView.getTag();
        }
        Club club = clubs.get(position);
        viewHolder.textView.setText(club.getName());
        final String imageUrlStr = club.getCrestUrl();

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    URL imgURL = new URL(imageUrlStr);
                    Bitmap bmp = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
                    System.out.println("Keep loading image....");
                    return bmp;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                viewHolder.imageView.setImageBitmap(bitmap);
            }
        };


        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
