package tvtran.com.vn.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;
import tvtran.com.vn.entity.City;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DistrictDetail;
import tvtran.com.vn.entity.DistrictGroupHeader;

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

  private static final String COMMA = ",";
  private static final String BLANK = "";

  private static final int THOUSANDS = 4;
  private static final int MILLIONS = 7;
  private static final int BILLIONS = 10;

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

  public static Double calculateTotalSalary(Double salaryVND, Double salaryUSD, Double currRate) {
    if (salaryUSD <= 0)
      return salaryVND;

    return salaryVND + (currRate*salaryUSD);
  }

  public static String moneyFormatter(String unformattedInput) throws NumberFormatException{

    final String replacedComma = unformattedInput.replaceAll(COMMA, BLANK);
    // for checking only
    Integer.parseInt(replacedComma);

    int numberOfChar = replacedComma.length();
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

    return tempStr;
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


  @NonNull
  public static String insertChar(String s, int pos, String charToInsert)
  {
    return s.substring(0, pos) + charToInsert + s.substring(pos, s.length());
  }

  @NonNull
  public static String insertComma(String s, int pos)
  {
    return insertChar(s, pos, ",");
  }
}
