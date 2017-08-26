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

  public static List<City> createCities()
  {
    final City hcm = new City(1, "Tp. Hồ Chí Minh");
    final City hn = new City(2, "Hà Nội");
    final City dn = new City(3, "Đà Nẵng");

    return Arrays.asList(hcm, hn, dn);
  }

  public static List<DistrictGroupHeader> createDistrictGroupHeaders(int cityId)
  {

    //@formatter:off
    return cityId == 1 ? Arrays.asList(
          new DistrictGroupHeader(1,  1, "Quận 1")
        , new DistrictGroupHeader(2 , 1, "Quận 2")
        , new DistrictGroupHeader(3 , 1, "Quận 3")
        , new DistrictGroupHeader(4 , 1, "Quận 4")
        , new DistrictGroupHeader(5 , 1, "Quận 5")
        , new DistrictGroupHeader(6 , 1, "Quận 6")
        , new DistrictGroupHeader(7 , 1, "Quận 7")
        , new DistrictGroupHeader(8 , 1, "Quận 8")
        , new DistrictGroupHeader(9 , 1, "Quận 9")
        , new DistrictGroupHeader(10, 1, "Quận 10")
        , new DistrictGroupHeader(11, 1, "Quận 11")
        , new DistrictGroupHeader(12, 1, "Quận 12")
        , new DistrictGroupHeader(13, 1, "Quận Bình Tân")
    ) : Arrays.asList(
          new DistrictGroupHeader(21,  2, "Quận 1")
        , new DistrictGroupHeader(22, 2, "Quận 2")
        , new DistrictGroupHeader(23, 2, "Quận 3")
        , new DistrictGroupHeader(24, 2, "Quận 4")
        , new DistrictGroupHeader(25, 2, "Quận 5")
        , new DistrictGroupHeader(26, 2, "Quận 6")
        , new DistrictGroupHeader(27, 2, "Quận 7")
        , new DistrictGroupHeader(28, 2, "Quận 8")
        , new DistrictGroupHeader(29, 2, "Quận 9")
        , new DistrictGroupHeader(30, 2, "Quận 10")
        , new DistrictGroupHeader(31, 2, "Quận 11")
        , new DistrictGroupHeader(32, 2, "Quận 12")
        , new DistrictGroupHeader(33, 2, "Quận Bình Tân")
    );

    //@formatter:on
  }

  public static Map<Integer, List<DistrictDetail>> createDistrictDetails(int cityId)
  {
    final Map<Integer, List<DistrictDetail>> districtDetailMap = new HashMap<>();

    if(cityId == 1) {
      districtDetailMap.put(1, Arrays.asList(new DistrictDetail(1, "Số 35 Lý Văn Phức, Phường Tân Định, Quận 1.", "0902548822", "")));
      districtDetailMap.put(2, Arrays.asList(new DistrictDetail(2, "400 Đồng Văn Cống - Phường Thạnh Mỹ Lợi - Quận 2", "0902548821", "")));
      districtDetailMap.put(3, Arrays.asList(new DistrictDetail(3, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(4, Arrays.asList(new DistrictDetail(4, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(5, Arrays.asList(new DistrictDetail(5, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(6, Arrays.asList(new DistrictDetail(6, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(7, Arrays.asList(new DistrictDetail(7, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(8, Arrays.asList(new DistrictDetail(8, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(9, Arrays.asList(new DistrictDetail(9, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(10, Arrays.asList(new DistrictDetail(10, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(11, Arrays.asList(new DistrictDetail(11, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(12, Arrays.asList(new DistrictDetail(12, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(13, Arrays.asList(new DistrictDetail(13, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
    } else {
      districtDetailMap.put(21, Arrays.asList(new DistrictDetail(21, "Số 351 Lý Văn Phức, Phường Tân Định, Quận 1.", "0902548822", "")));
      districtDetailMap.put(22, Arrays.asList(new DistrictDetail(22, "400 Đồng Văn Cống - Phường Thạnh Mỹ Lợi - Quận 2", "0902548821", "")));
      districtDetailMap.put(23, Arrays.asList(new DistrictDetail(23, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(24, Arrays.asList(new DistrictDetail(24, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(25, Arrays.asList(new DistrictDetail(25, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
      districtDetailMap.put(26, Arrays.asList(new DistrictDetail(26, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079", "")));
    }


    return districtDetailMap;
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
