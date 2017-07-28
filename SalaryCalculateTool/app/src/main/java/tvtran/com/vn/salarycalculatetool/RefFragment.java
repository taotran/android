package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import tvtran.com.vn.adapter.CitySpinnerAdapter;
import tvtran.com.vn.adapter.ExpandableDistrictAdapter;
import tvtran.com.vn.utils.Utils;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/26/2017
 */
public class RefFragment extends Fragment
{

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_ref, container, false);
  }

  @Override
  public void onResume()
  {
    final Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.spinnerCity);

    //final String[] cities = new String[] {"Ho Chi Minh", "Ha Noi", "Da Nang"};

    citySpinner.setAdapter(new CitySpinnerAdapter(getContext(), Utils.createCities()));

    ExpandableDistrictAdapter adapter = new ExpandableDistrictAdapter(getContext(), Utils.createDistrictGroupHeaders(), Utils.createDistrictDetails());

    ExpandableListView listView = (ExpandableListView) getActivity().findViewById(R.id.expandableDistrictInCity);

    listView.setAdapter(adapter);


    super.onResume();
  }
}
