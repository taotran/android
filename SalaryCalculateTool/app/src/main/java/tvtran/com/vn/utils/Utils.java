package tvtran.com.vn.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import tvtran.com.vn.entity.City;
import tvtran.com.vn.entity.Detail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

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

  public static void hideSoftKeyboard(Activity activity)
  {
    InputMethodManager inputMethodManager =
        (InputMethodManager) activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE);
    if (activity.getCurrentFocus() != null) {
      inputMethodManager.hideSoftInputFromWindow(
          activity.getCurrentFocus().getWindowToken(), 0);
    }
  }


  //TODO: improvement needed!!!
  public static void writeContentToDetailList(List<Detail> details, int index, String value, String value2)
  {
    for (Detail detail : details) {
      if (detail.getId() == index) {
        detail.setValue(value);
        detail.setValue2(value2);
        return;
      }
    }
  }

  //TODO: improvement needed!!!
  public static void writeContentToDetailList(List<Detail> details, int index, String value)
  {
    writeContentToDetailList(details, index, value, "");
  }

  public static Map<Integer, List<Detail>> initDetailsMap()
  {
    final Map<Integer, List<Detail>> detailsMap = new HashMap<>();

    final List<Detail> detailList = new ArrayList<>();
    //@formatter:off
    detailList.add(new Detail("Lương GROSS"                                     , 0));
    detailList.add(new Detail("Bảo hiểm xã hội (8%)"                            , 1));
    detailList.add(new Detail("Bảo hiểm y tế (1.5%)"                            , 2));
    detailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", 3));
    detailList.add(new Detail("Thu nhập trước thuế"                             , 4));
    detailList.add(new Detail("Giảm trừ gia cảnh bản thân"                      , 5));
    detailList.add(new Detail("Giảm trừ gia cảnh người phụ thuộc"               , 6));
    detailList.add(new Detail("Thu nhập chịu thuế"                              , 7));
    detailList.add(new Detail("Thuế thu nhập cá nhân (*)"                       , 8));
    detailList.add(new Detail("Lương NET"                                       , 9));
    //@formatter:on

    detailsMap.put(1, detailList);

    final List<Detail> detailTNCNList = new ArrayList<>();

    //@formatter:off
    detailTNCNList.add(new Detail("Đến  5 triệu VND"            , 0));
    detailTNCNList.add(new Detail("Trên 5 triệu  - 10 triệu VND", 1));
    detailTNCNList.add(new Detail("Trên 10 triệu - 18 triệu VND", 2));
    detailTNCNList.add(new Detail("Trên 18 triệu - 32 triệu VND", 3));
    detailTNCNList.add(new Detail("Trên 32 triệu - 52 triệu VND", 4));
    detailTNCNList.add(new Detail("Trên 52 triệu - 80 triệu VND", 5));
    detailTNCNList.add(new Detail("Trên 80 triệu VND"           , 6));
    //@formatter:on

    detailsMap.put(2, detailTNCNList);

    final List<Detail> employerDetailList = new ArrayList<>();

    //@formatter:off
    employerDetailList.add(new Detail("Lương GROSS"                                     , 0));
    employerDetailList.add(new Detail("Bảo hiểm xã hội (17.5%)"                         , 1));
    employerDetailList.add(new Detail("Bảo hiểm y tế (3%)"                              , 2));
    employerDetailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", 3));
    employerDetailList.add(new Detail("Tổng cộng"                                       , 4));
    //@formatter:on

    detailsMap.put(3, employerDetailList);

    return detailsMap;

  }

  public static String formattedDouble(double number)
  {
    if (number < 1000) {
      return String.valueOf(number);
    }
    try {
      NumberFormat formatter = new DecimalFormat("###,###");
      String resp = formatter.format(number);
//      resp = resp.replaceAll(",", ".");
      return resp;
    } catch (Exception e) {
      return "";
    }
  }

  public static List<City> createCities()
  {
    final City hcm = new City(1, "HCM");
    final City hn = new City(2, "HN");
    final City dn = new City(3, "DN");

    return Arrays.asList(hcm, hn, dn);
  }
}
