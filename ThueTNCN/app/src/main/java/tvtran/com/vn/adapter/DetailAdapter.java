package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.thuetncn.R;

import java.util.List;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/2/2017
 */
public class DetailAdapter extends ArrayAdapter<Detail>
{
  private List<Detail> details;
  private LayoutInflater inflater;

  public DetailAdapter(Context context, List<Detail> objects)
  {
    super(context, 0, objects);
    this.details = objects;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_detail_item, parent, false);

      viewHolder = new ViewHolder();
      viewHolder.detailKeyTextView = (TextView) convertView.findViewById(R.id.detailKey);
      viewHolder.detailValueTextView = (TextView) convertView.findViewById(R.id.detailValue);

      convertView.setTag(viewHolder);
    }
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }
    Detail detail = details.get(position);
    viewHolder.detailKeyTextView.setText(detail.getKey());
    viewHolder.detailValueTextView.setText(detail.getValue());
    return convertView;
  }

  static class ViewHolder
  {
    TextView detailKeyTextView;
    TextView detailValueTextView;
  }
}
