package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import tvtran.com.vn.entity.City;
import tvtran.com.vn.salarycalculatetool.R;

import java.util.List;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/27/2017
 */
public class CitySpinnerAdapter extends ArrayAdapter<City>
{
  private LayoutInflater inflater;

  public CitySpinnerAdapter(Context context, List<City> objects)
  {
    super(context, 0, objects);
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
//    final Conve
//    ViewHolder viewHolder = (ViewHolder) build(convertView).getTag();
//    viewHolder.cityNameTextView.setText(getItem(position).getName());

    return build(convertView, position);
  }

  @Override
  public View getDropDownView(int position, View convertView, ViewGroup parent)
  {
    ViewHolder viewHolder = (ViewHolder) build(convertView, position).getTag();

    return viewHolder.cityNameTextView;
  }

  private View build(View convertView, int position)
  {
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_city_item, null);

      viewHolder = new ViewHolder();
      viewHolder.cityNameTextView = (TextView) convertView.findViewById(R.id.spinnerCityItemTextView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder)convertView.getTag();
    }
    viewHolder.cityNameTextView.setText(getItem(position).getName());
    return convertView;
  }

  private static class ViewHolder
  {
    TextView cityNameTextView;
  }
}
