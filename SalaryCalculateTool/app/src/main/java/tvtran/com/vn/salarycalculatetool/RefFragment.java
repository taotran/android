package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import tvtran.com.vn.adapter.CitySpinnerAdapter;
import tvtran.com.vn.adapter.ExpandableDistrictAdapter;
import tvtran.com.vn.entity.City;
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
    final View view = inflater.inflate(R.layout.fragment_ref, container, false);
    MobileAds.initialize(getContext(), "adView1");
    AdView adView = (AdView)view.findViewById(R.id.adView1);

    //ca-app-pub-7600696968336513~9499349953
    //A007382C43BF8253883D93971C7FAAE4
    //ThueTNCNAd
    //AdRequest adRequest = new AdRequest.Builder().addTestDevice("A007382C43BF8253883D93971C7FAAE4").build();
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
    return view;

  }

  @Override
  public void onResume()
  {
    final Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.spinnerCity);

    citySpinner.setAdapter(new CitySpinnerAdapter(getContext(), Utils.createCities()));

    final int selectedCityId = ((City) citySpinner.getSelectedItem()).getId();

    final ExpandableDistrictAdapter adapter = new ExpandableDistrictAdapter(getContext(), Utils.createDistrictGroupHeaders(selectedCityId), Utils.createDistrictDetails(selectedCityId));

    final ExpandableListView listView = (ExpandableListView) getActivity().findViewById(R.id.expandableDistrictInCity);

    listView.setAdapter(adapter);

    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
      {
        adapter.setNewItems(Utils.createDistrictGroupHeaders(selectedCityId), Utils.createDistrictDetails(selectedCityId));
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent)
      {

      }
    });


    super.onResume();
  }
}
