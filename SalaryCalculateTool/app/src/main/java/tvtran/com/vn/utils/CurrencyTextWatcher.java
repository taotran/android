package tvtran.com.vn.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/28/2017
 */
public class CurrencyTextWatcher implements TextWatcher
{
  private static final String BLANK = "";

  private boolean mEditing;

  public CurrencyTextWatcher()
  {
    mEditing = false;
  }

  //TODO: optimization needed!!!
  public synchronized void afterTextChanged(Editable s)
  {
    if (!mEditing) {
      mEditing = true;
      try {
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

}