package tvtran.com.vn.salarycalculatetool;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/6/2017
 */
public class CalcFragment extends Fragment
{

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.salary_calc_fragment, container, false);
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
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

    getActivity().findViewById(R.id.resultTextView).setVisibility(View.GONE);

    salaryEditText.addTextChangedListener(new CurrencyTextWatcher());

    super.onResume();


  }

  class CurrencyTextWatcher implements TextWatcher
  {

    boolean mEditing;

    public CurrencyTextWatcher()
    {
      mEditing = false;
    }

    public synchronized void afterTextChanged(Editable s)
    {
//      if (!mEditing) {
//        mEditing = true;
//
//        String digits = s.toString().replaceAll("\\D", "");
//        NumberFormat nf = NumberFormat.getCurrencyInstance();
//        try {
//          String formatted = nf.format(Double.parseDouble(digits) / 100);
//          s.replace(0, s.length(), formatted);
//        } catch (NumberFormatException nfe) {
//          s.clear();
//        }
//
//        mEditing = false;
//      }


    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
      String ss = "10000";
      System.out.println(s);
    }

  }
}
