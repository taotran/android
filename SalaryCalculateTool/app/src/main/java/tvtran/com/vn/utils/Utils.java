package tvtran.com.vn.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import tvtran.com.vn.entity.Detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/2/2017
 */
public class Utils
{

  public static List<Detail> init()
  {
    final List<Detail> details = new ArrayList<>();

//    details.add(new Detail("Lương GROSS", "10.0000", "DDCT"));
//    details.add(new Detail("Lương GROSS 1", "10.0000", "DDCT"));
//    details.add(new Detail("Lương GROSS 2", "10.0000", "DDCT"));
//    details.add(new Detail("Lương GROSS 3", "10.0000", "DDCT"));

    return details;
  }

  public static void hideSoftKeyboard(Activity activity) {
    InputMethodManager inputMethodManager =
        (InputMethodManager) activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(
        activity.getCurrentFocus().getWindowToken(), 0);
  }
}
