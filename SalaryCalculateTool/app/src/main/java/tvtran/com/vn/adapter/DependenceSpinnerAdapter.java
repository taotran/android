package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import tvtran.com.vn.salarycalculatetool.R;

import java.util.List;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/6/2017
 */
public class DependenceSpinnerAdapter extends ArrayAdapter<Integer>
{

  private final List<Integer> numbers;

  public DependenceSpinnerAdapter(Context context, List<Integer> numbers)
  {
    super(context, 0);
    this.numbers = numbers;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.dependence_spinner_item, parent, false);
    }
//    final EditText spinnerItem = (EditText) convertView.findViewById(R.id.spinnerItemTextView);
//    spinnerItem.setText(numbers.get(position));
    return convertView;
  }
}
