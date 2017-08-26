package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import tvtran.com.vn.utils.Utils;

import static tvtran.com.vn.utils.Utils.insertComma;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/6/2017
 */
public class CalcFragment extends Fragment
{

  private static final String DEFAULT_FINAL_RESULT_TEXT = "Salary Amount";

  private static final String COMMA = ",";
  private static final String BLANK = "";

  private static final int THOUSANDS = 4;
  private static final int MILLIONS = 7;
  private static final int BILLIONS = 10;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_salary_calc, container, false);
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

  class CurrencyTextWatcher implements TextWatcher
  {

    boolean mEditing;

    CurrencyTextWatcher()
    {
      mEditing = false;
    }

    //TODO: optimization needed!!!
    public synchronized void afterTextChanged(Editable s)
    {
      if (!mEditing) {
        mEditing = true;
//        final String replacedComma = s.toString().replaceAll(COMMA, BLANK);

        try {
          // for checking only
//          Integer.parseInt(replacedComma);

//          int numberOfChar = replacedComma.length();
//          String tempStr = s.toString();
//          //1.000 -> 100.000
//          if (numberOfChar >= THOUSANDS && numberOfChar < MILLIONS) {
//            int commaPos = numberOfChar - 3;
//            tempStr = insertComma(replacedComma, commaPos);
//          }
//          //1.000.000 -> 100.000.000
//          else if (numberOfChar >= MILLIONS && numberOfChar < BILLIONS) {
//            int firstComma = numberOfChar - 6;
//            int secondComma = numberOfChar - 2;
//
//            tempStr = insertComma(replacedComma, firstComma);
//            tempStr = insertComma(tempStr, secondComma);
//          }
//          //1.000.000.000
//          else if (numberOfChar >= BILLIONS) {
//            int firstComma = numberOfChar - 9;
//            int secondComma = numberOfChar - 5;
//            int thirdComma = numberOfChar - 1;
//            tempStr = insertComma(replacedComma, firstComma);
//            tempStr = insertComma(tempStr, secondComma);
//            tempStr = insertComma(tempStr, thirdComma);
//          }
          s.replace(0, s.length(), Utils.moneyFormatter(s.toString()));

        } catch (NumberFormatException e) {
          s.replace(0, s.length(), s.toString().replaceAll("[a-zA-Z]", BLANK));
        } finally {
          mEditing = false;
        }
      }


    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
    }

    /*String moneyFormatter(String unformattedInput) {
      final String replacedComma = unformattedInput.replaceAll(COMMA, BLANK);
      int numberOfChar = unformattedInput.length();
      String tempStr = unformattedInput;
      //1.000 -> 100.000
      if (numberOfChar >= THOUSANDS && numberOfChar < MILLIONS) {
        int commaPos = numberOfChar - 3;
        tempStr = insertComma(replacedComma, commaPos);
      }
      //1.000.000 -> 100.000.000
      else if (numberOfChar >= MILLIONS && numberOfChar < BILLIONS) {
        int firstComma = numberOfChar - 6;
        int secondComma = numberOfChar - 2;

        tempStr = insertComma(replacedComma, firstComma);
        tempStr = insertComma(tempStr, secondComma);
      }
      //1.000.000.000
      else if (numberOfChar >= BILLIONS) {
        int firstComma = numberOfChar - 9;
        int secondComma = numberOfChar - 5;
        int thirdComma = numberOfChar - 1;
        tempStr = insertComma(replacedComma, firstComma);
        tempStr = insertComma(tempStr, secondComma);
        tempStr = insertComma(tempStr, thirdComma);
      }
    }*/

  }

}
