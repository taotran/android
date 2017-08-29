package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tvtran.com.vn.entity.DistrictDetail;
import tvtran.com.vn.entity.DistrictGroupHeader;
import tvtran.com.vn.salarycalculatetool.R;

import java.util.List;
import java.util.Map;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/28/2017
 */
public class ExpandableDistrictAdapter extends AbstractExpandableListAdapter<DistrictGroupHeader, DistrictDetail>
{
  public ExpandableDistrictAdapter(Context context, List<DistrictGroupHeader> listDataHeader, Map<Integer, List<DistrictDetail>> _listDataChild)
  {
    super(context, listDataHeader, _listDataChild);
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
  {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.district_group_header, null);
    }
    ((TextView) convertView.findViewById(R.id.districtGroupHeaderTextView)).setText(getGroup(groupPosition).getDisplayValue());
    return convertView;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder;
    final DistrictDetail child = getChild(groupPosition, childPosition);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_detail_district_item, null);
      viewHolder = new ViewHolder();
      viewHolder.addressTextView = (TextView) convertView.findViewById(R.id.address);
      viewHolder.contactTextView = (TextView) convertView.findViewById(R.id.contactInfo);
      viewHolder.otherTextView = (TextView) convertView.findViewById(R.id.others);

      convertView.setTag(viewHolder);
    }
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.addressTextView.setText(child.getAddress());
    viewHolder.contactTextView.setText(child.getContactInfo());
    viewHolder.otherTextView.setText(child.getOthers());


    return convertView;
  }


  @Override
  public long getGroupId(int groupPosition)
  {
    return _listDataHeader.get(groupPosition).getCityId();
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition)
  {
    return false;
  }

  private static class ViewHolder
  {
    TextView addressTextView;
    TextView contactTextView;
    TextView otherTextView;
  }

  public void setNewItems(List<DistrictGroupHeader> listDataHeader, Map<Integer, List<DistrictDetail>> listDataChild) {
    this._listDataHeader = listDataHeader;
    this._listDataChild = listDataChild;
    notifyDataSetChanged();
  }
}
