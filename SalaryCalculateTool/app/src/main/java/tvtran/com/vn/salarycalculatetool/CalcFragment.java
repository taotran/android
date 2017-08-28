package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import tvtran.com.vn.utils.CurrencyTextWatcher;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/6/2017
 */
public class CalcFragment extends Fragment
{

  private static final String DEFAULT_FINAL_RESULT_TEXT = "Salary Amount";

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    final View view = inflater.inflate(R.layout.fragment_salary_calc, container, false);
    MobileAds.initialize(getContext(), "adView");
    AdView adView = (AdView)view.findViewById(R.id.adView);
    adView.setAdListener(new AdListener() {
      @Override
      public void onAdLoaded()
      {
        System.out.println("add loaded");
        super.onAdLoaded();
      }

      @Override
      public void onAdFailedToLoad(int i)
      {
        System.out.println("ad failed to load");
      }
    });

    //ca-app-pub-7600696968336513~9499349953
    //A007382C43BF8253883D93971C7FAAE4
    //ThueTNCNAd
//    AdRequest adRequest = new AdRequest.Builder().addTestDevice("A007382C43BF8253883D93971C7FAAE4").build();
    AdRequest adRequest = new AdRequest.Builder().build();
//    adRequest.isTestDevice(getContext());
    adView.loadAd(adRequest);
    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

  }



  @Override
  public void onResume()
  {
    final Spinner spinner = (Spinner) getActivity().findViewById(R.id.dependenceSpinner);
    final Integer[] spinnerArray = new Integer[]{0, 1, 2, 3, 4};
    final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.dependence_spinner_item, spinnerArray);

    spinner.setAdapter(arrayAdapter);

    final EditText salaryEditText = (EditText) getActivity().findViewById(R.id.salaryEditText);
    salaryEditText.addTextChangedListener(new CurrencyTextWatcher());

    final String finalResultText = ((TextView) getActivity().findViewById(R.id.resultTextView)).getText().toString();
    if (DEFAULT_FINAL_RESULT_TEXT.equals(finalResultText)) {
      getActivity().findViewById(R.id.resultTextView).setVisibility(View.GONE);
      getActivity().findViewById(R.id.finalResultTextView).setVisibility(View.GONE);
    }
    super.onResume();
  }

  @Override
  public void onStart()
  {
    super.onStart();
  }

}
