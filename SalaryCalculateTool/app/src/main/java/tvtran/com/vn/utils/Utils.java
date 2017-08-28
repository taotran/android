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
          new DistrictGroupHeader(1 , 1, "BHXH Quận 1")
        , new DistrictGroupHeader(2 , 1, "BHXH Quận 2")
        , new DistrictGroupHeader(3 , 1, "BHXH Quận 3")
        , new DistrictGroupHeader(4 , 1, "BHXH Quận 4")
        , new DistrictGroupHeader(5 , 1, "BHXH Quận 5")
        , new DistrictGroupHeader(6 , 1, "BHXH Quận 6")
        , new DistrictGroupHeader(7 , 1, "BHXH Quận 7")
        , new DistrictGroupHeader(8 , 1, "BHXH Quận 8")
        , new DistrictGroupHeader(9 , 1, "BHXH Quận 9")
        , new DistrictGroupHeader(10, 1, "BHXH Quận 10")
        , new DistrictGroupHeader(11, 1, "BHXH Quận 11")
        , new DistrictGroupHeader(12, 1, "BHXH Quận 12")
        , new DistrictGroupHeader(13, 1, "BHXH Quận Bình Tân")
        , new DistrictGroupHeader(14, 1, "BHXH Quận Bình Thạnh")
        , new DistrictGroupHeader(15, 1, "BHXH Quận Gò Vấp")
        , new DistrictGroupHeader(16, 1, "BHXH Quận Phú Nhuận")
        , new DistrictGroupHeader(17, 1, "BHXH Quận Tân Bình")
        , new DistrictGroupHeader(18, 1, "BHXH Quận Tân Phú")
        , new DistrictGroupHeader(19, 1, "BHXH Quận Thủ đức")
        , new DistrictGroupHeader(20, 1, "BHXH Quận Bình Chánh")
        , new DistrictGroupHeader(21, 1, "BHXH Quận Cần Giờ")
        , new DistrictGroupHeader(22, 1, "BHXH Quận Củ Chi")
        , new DistrictGroupHeader(23, 1, "BHXH Hóc Môn")
        , new DistrictGroupHeader(24, 1, "BHXH Nhà Bè")
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
      districtDetailMap.put(1 , Arrays.asList(new DistrictDetail(1, "Số 35 Lý Văn Phức, Phường Tân Định, Quận 1.", "028.38203747 - Ext: 113 hoặc Ext: 114", "")));
      districtDetailMap.put(2 , Arrays.asList(new DistrictDetail(2, "400 Đồng Văn Cống - Phường Thạnh Mỹ Lợi - Quận 2", "3.743.0056(101)-3.743.0056(102)-3742.0469(103)-3.743.0056(104)", "")));
      districtDetailMap.put(3 , Arrays.asList(new DistrictDetail(3, "386/79 đường Lê Văn Sỹ phường 14 quận 3 Tp.HCM.", "38465079(207)", "")));
      districtDetailMap.put(4 , Arrays.asList(new DistrictDetail(4, "64 Nguyễn Tất Thành, P12, Q4", "38.264.979", "")));
      districtDetailMap.put(5 , Arrays.asList(new DistrictDetail(5, "187 Lương Nhữ Học, P.11, Q.5, TP.HCM", "08-3853.5285", "")));
      districtDetailMap.put(6 , Arrays.asList(new DistrictDetail(6, "152 Phạm Văn Chí, phường 4, quận 6", "3.854.3385, 3.854.3392", "")));
      districtDetailMap.put(7 , Arrays.asList(new DistrictDetail(7, "136 Huỳnh Tấn Phát, phường Tân Thuận Tây, Quận 7", "37850108 (106 - 118 - 138)", "")));
      districtDetailMap.put(8 , Arrays.asList(new DistrictDetail(8, "số 9 Đường 1011 Phường 5 Quận 8.", "3.8500750 (115 - 111)", "")));
      districtDetailMap.put(9 , Arrays.asList(new DistrictDetail(9, "442 đường Lê Văn Việt, phường Tăng Nhơn Phú A, Quận 9", "37361890", "")));
      districtDetailMap.put(10, Arrays.asList(new DistrictDetail(10, "781 Lê Hồng Phong (nối dài), P.12,Q.10", "38620799; 38632879 - Ext 104,105", "")));
      districtDetailMap.put(11, Arrays.asList(new DistrictDetail(11, "5-7 Hàn Hải Nguyên, Phường 16, Quận 11, TPHCM", "028.39 699 683", "")));
      districtDetailMap.put(12, Arrays.asList(new DistrictDetail(12, "Số 314 đường Lê Thị Riêng, P. Thới An, Q.12", "028 37174522", "")));
      districtDetailMap.put(13, Arrays.asList(new DistrictDetail(13, "530 Kinh Dương Vương, P. An Lạc A, Q. Bình Tân", "028.38754475, số nội bộ: 109", "")));
      districtDetailMap.put(14, Arrays.asList(new DistrictDetail(14, "30 Nguyễn Thiện Thuật, Phường 24, Quận Bình Thạnh", "35510125 line 102", "")));
      districtDetailMap.put(15, Arrays.asList(new DistrictDetail(15, "135 Phạm Văn Đồng, phường 3, quận Gò Vấp, TPHCM", "39853.844 ext 101,102,103,104", "")));
      districtDetailMap.put(16, Arrays.asList(new DistrictDetail(16, "40G Phan Đình Phùng, Phường 2, Quận Phú Nhuận", "39.951.764 - Ext: 101; 102; 103;104", "")));
      districtDetailMap.put(17, Arrays.asList(new DistrictDetail(17, "300 Nguyễn Trọng Tuyển, phường 01, quận Tân Bình", "3997 4036", "")));
      districtDetailMap.put(18, Arrays.asList(new DistrictDetail(18, "Số 52/30 Thoại Ngọc Hầu, Phường Hoà Thạnh, Quận Tân Phú", "39.760.381", "")));
      districtDetailMap.put(19, Arrays.asList(new DistrictDetail(19, "22 đường số 6, khu phố 5, phường Linh Chiểu, quận Thủ Đức", "38962585-Nhánh 106; HS điện tử: Nhánh 102, 210", "")));
      districtDetailMap.put(20, Arrays.asList(new DistrictDetail(20, "Số 1 đường số 4, Trung tâm hành chính huyện, TT. Tân Túc, huyện Bình Chánh", " (028) 37602303 nhánh 124", "")));
      districtDetailMap.put(21, Arrays.asList(new DistrictDetail(21, "đường Lương Văn Nho, TT Cần Thạnh, H. Cần Giờ, TP.HCM", "0283.8740.473", "")));
      districtDetailMap.put(22, Arrays.asList(new DistrictDetail(22, "Khu phố 2 Thị trấn Củ Chi, huyện Củ Chi, TPHCM", "08.38920913; GĐ: Vân 101; PGĐ: Khuê 103, Kiệt 102; Kiểm tra 117; Giao dịch Điện tử: 08.37924631 ", "")));
      districtDetailMap.put(23, Arrays.asList(new DistrictDetail(23, "26 Lê Thị Hà, Tân Xuân, Hóc Môn", "(028) 3.8914.366 Ext 115.", "")));
      districtDetailMap.put(24, Arrays.asList(new DistrictDetail(24, "424/4 Đường Nguyễn Bình, Ấp 1, xã Phú Xuân, Huyện Nhà Bè", "028.37770061- 63 (108-111-114)  ", "")));

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
