package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;
import tvtran.com.vn.salarycalculatetool.R;

import java.util.List;
import java.util.Map;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/5/2017
 */
public class ExpandableDetailAdapter extends AbstractExpandableListAdapter<DetailGroupHeader, Detail>
{
  private LayoutInflater inflater;

  public ExpandableDetailAdapter(Context context, List<DetailGroupHeader> listDataHeader, Map<Integer, List<Detail>> _listDataChild)
  {
    super(context, listDataHeader, _listDataChild);
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
  {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.detail_group_header, null);
    }

    final TextView headerTextView = (TextView) convertView.findViewById(R.id.detailGroupHeaderTextView);
    headerTextView.setText(getGroup(groupPosition).getDisplayValue());

    return convertView;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
  {
    ListItemViewHolder viewHolder;
    final Detail detail = getChild(groupPosition, childPosition);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_detail_item, null);
      viewHolder = new ListItemViewHolder();
      viewHolder.detailKeyTextView = (TextView) convertView.findViewById(R.id.detailKey);
      viewHolder.detailValueTextView = (TextView) convertView.findViewById(R.id.detailValue);
      viewHolder.detailValue2TextView = (TextView) convertView.findViewById(R.id.detailValue2);
      convertView.setTag(viewHolder);
    }
    viewHolder = (ListItemViewHolder) convertView.getTag();

    viewHolder.detailKeyTextView.setText(detail.getKey());
    viewHolder.detailValueTextView.setText(detail.getValue());
//    if (detail.getValue2() != null && !detail.getValue2().trim().isEmpty()) {
//      viewHolder.detailValue2TextView.setText(detail.getValue2());
//      viewHolder.detailValue2TextView.setVisibility(View.VISIBLE);
//    }
//    else {
//      viewHolder.detailValue2TextView.setVisibility(View.GONE);
//    }
    viewHolder.detailValue2TextView.setText(detail.getValue2());


    return convertView;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition)
  {
    return false;
  }

  private static class ListItemViewHolder
  {
    TextView detailKeyTextView;
    TextView detailValueTextView;
    TextView detailValue2TextView;
  }
}
