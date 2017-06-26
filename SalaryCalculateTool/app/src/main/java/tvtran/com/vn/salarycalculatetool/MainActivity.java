package tvtran.com.vn.salarycalculatetool;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import tvtran.com.vn.adapter.ExpandableDetailAdapter;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;
import tvtran.com.vn.utils.Utils;

import java.util.*;

public class MainActivity extends AppCompatActivity
{
  private static final double BHXH = 0.08;
  private static final double BHYT = 0.015;
  private static final double BHTN = 0.01;

  private static final double MIN_SAL = 1210000;

  private static final double MAX_SAL_FOR_BHXH_BHYT = MIN_SAL * 20; // = 24.200.000 VND

  private static final double MAX_BHXH_BHXH = 1936000; //for salary > MAX_SAL_FOR_BHXH_BHYT
  private static final double MAX_BHXH_BHYT = 363000; //for salary > MAX_SAL_FOR_BHXH_BHYT


  //@formatter:off
  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;
  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000;
  private static final double GIA_CANH_PHU_THUOC          = 3600000;

  private static final double N2G_UNDER_TAX_RANGE_1       = 4750000;
  private static final double N2G_UNDER_TAX_RANGE_2       = 9250000;
  private static final double N2G_UNDER_TAX_RANGE_3       = 16050000;
  private static final double N2G_UNDER_TAX_RANGE_4       = 27250000;
  private static final double N2G_UNDER_TAX_RANGE_5       = 42250000;
  private static final double N2G_UNDER_TAX_RANGE_6       = 61850000;

  private static final double N2G_UNDER_TAX_CONST_1       = 0.95;
  private static final double N2G_UNDER_TAX_CONST_2       = 0.9;
  private static final double N2G_UNDER_TAX_CONST_3       = 0.85;
  private static final double N2G_UNDER_TAX_CONST_4       = 0.8;
  private static final double N2G_UNDER_TAX_CONST_5       = 0.75;
  private static final double N2G_UNDER_TAX_CONST_6       = 0.70;
  private static final double N2G_UNDER_TAX_CONST_7       = 0.65;

  private Map<DetailGroupHeader, List<Detail>> detailsMap;
  private List<DetailGroupHeader> headers;
  //@formatter:on
  private List<Detail> detailList = new ArrayList<>();
  private List<Detail> detailTNCNList = new ArrayList<>();
  private List<Detail> employerDetailList = new ArrayList<>();


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

    fragmentTransaction.replace(R.id.fragment_container, new CalcFragment());
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();


  }

  @Override
  protected void onResume()
  {
    super.onResume();
  }


  public void onGrossToNetClick(View view)
  {
    System.out.println("You just click calculate");
    Utils.hideSoftKeyboard(this);

    final String salaryString = ((EditText) findViewById(R.id.salaryEditText)).getText().toString();
    final String noOfDependence = ((Spinner) findViewById(R.id.dependenceSpinner)).getSelectedItem().toString();

    final List<DetailGroupHeader> headers = new ArrayList<>();
    DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
    DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
    DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");

    headers.add(detailGroupHeader);
    headers.add(detailGroupHeader1);
    headers.add(detailGroupHeader2);

    //FULL Calculation
    final Double finalG2NResult = calculateFinalG2NSalary(salaryString, noOfDependence);

    final Map<DetailGroupHeader, List<Detail>> details = new HashMap<>();

//    final List<Detail> detailList = new ArrayList<>();
//    //@formatter:off
//    detailList.add(new Detail("Lương GROSS"                                     , "24,600,000", "", 0));
//    detailList.add(new Detail("Bảo hiểm xã hội (8%)"                            , "24,600,000", "", 1));
//    detailList.add(new Detail("Bảo hiểm y tế (1.5%)"                            , "24,600,000", "", 2));
//    detailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", "24,600,000", "", 3));
//    detailList.add(new Detail("Thu nhập trước thuế"                             , "24,600,000", "", 4));
//    detailList.add(new Detail("Giảm trừ gia cảnh bản thân"                      , "24,600,000", "", 5));
//    detailList.add(new Detail("Giảm trừ gia cảnh người phụ thuộc"               , "24,600,000", "", 6));
//    detailList.add(new Detail("Thu nhập chịu thuế"                              , "24,600,000", "", 7));
//    detailList.add(new Detail("Thuế thu nhập cá nhân (*)"                       , "24,600,000", "", 8));
//    detailList.add(new Detail("Lương NET"                                       , "24,600,000", "", 9));

    details.put(detailGroupHeader, detailList);

    //@formatter:on

    final ExpandableDetailAdapter detailAdapter = new ExpandableDetailAdapter(this, headers, details);

    final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detailExpandableListView);

    expandableListView.setAdapter(detailAdapter);


    new AlertDialog.Builder(this)
        .setTitle("Kết quả")
        .setMessage("Lương Net: " + String.format("%.0f", finalG2NResult))
        .setCancelable(true)
        .show();

  }

  public void onNetToGrossClick(View view)
  {

  }


  private void initDetailListViewData()
  {
    detailsMap = new HashMap<>();
    headers = new ArrayList<>();
    final DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
    final DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
    final DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");


    headers.add(detailGroupHeader);
    headers.add(detailGroupHeader1);
    headers.add(detailGroupHeader2);


  }


  private double calculateFinalG2NSalary(String salaryString, String numberOfDependenceString)
  {
    double result = 0f;
    double salaryAfterInsurances = 0f;
    double salaryAfterDependencies = 0f;

    final Double salary = new Double(salaryString.toString());
    final Integer noDependencies = Integer.valueOf(numberOfDependenceString);

    detailList.add(new Detail("Lương GROSS"                                     , formattedDouble(salary), "", 0));
    salaryAfterInsurances = calcSalaryAfterAppliedInsurances(salary);
    detailList.add(new Detail("Thu nhập trước thuế", formattedDouble(salaryAfterInsurances), "", 4));
    if (noDependencies > 0) {
      salaryAfterDependencies = salaryAfterInsurances - GIAM_TRU_GIA_CANH_BAN_THAN - (noDependencies * GIA_CANH_PHU_THUOC);
    }
    else {
      salaryAfterDependencies = salaryAfterInsurances - GIAM_TRU_GIA_CANH_BAN_THAN;
    }

    detailList.add(new Detail("Giảm trừ gia cảnh bản thân", "9000000", "", 5));
    detailList.add(new Detail("Giảm trừ gia cảnh người phụ thuộc", formattedDouble(salaryAfterDependencies), "", 6));
    detailList.add(new Detail("Thu nhập chịu thuế", formattedDouble(salaryAfterInsurances - (9000000 + salaryAfterDependencies)), "", 7));

    final Double thueTNCNByRange = calcThueTNCNByRange(salaryAfterDependencies);
    result = salaryAfterInsurances - thueTNCNByRange;
    detailList.add(new Detail("Thuế thu nhập cá nhân (*)", formattedDouble(thueTNCNByRange), "", 8));
    detailList.add(new Detail("Lương NET", formattedDouble(result), "", 9));

    return result;
  }

  private Double calcSalaryAfterAppliedInsurances(Double grossSalary)
  {
    final double appliedBHXH = grossSalary * BHXH;
    final double appliedBHYT = grossSalary * BHYT;
    final double appliedBHTN = grossSalary * BHTN;


    final double salaryBeforeTax = grossSalary - (appliedBHXH + appliedBHYT + appliedBHTN);

    //add to detail list
    detailList.add(new Detail("Bảo hiểm xã hội (8%)", formattedDouble(appliedBHXH), "", 1));
    detailList.add(new Detail("Bảo hiểm y tế (1.5%)", formattedDouble(appliedBHYT), "", 2));
    detailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", formattedDouble(appliedBHTN), "", 3));

    return salaryBeforeTax;
  }

  private String formattedDouble(Double value)
  {
    return String.format(new Locale("vi", "VN"), "%.0f", value);
  }

  /*
  ----- (*) Chi tiết thuế thu nhập cá nhân (VND)
  ----- Mức chịu thuế	Thuế suất	Tiền nộp
  ----- Đến   5 triệu VND	                   5%	250,000
  ----- Trên  5 triệu VND đến 10 triệu VND	10%	500,000
  ----- Trên 10 triệu VND đến 18 triệu VND	15%	458,250
  ----- Trên 18 triệu VND đến 32 triệu VND	20%	0
  ----- Trên 32 triệu VND đến 52 triệu VND	25%	0
  ----- Trên 52 triệu VND đến 80 triệu VND	30%	0
  */
  private Double calcThueTNCNByRange(Double salaryAfterTax)
  {
    double taxTotal = 0f;
    if ((salaryAfterTax >= EIGHTY_MILIONS_RANGE) || (salaryAfterTax > FIFTY_TWO_MILIONS_RANGE && salaryAfterTax < EIGHTY_MILIONS_RANGE)) {
      taxTotal += (salaryAfterTax - FIFTY_TWO_MILIONS_RANGE) * 0.30;
      taxTotal += THIRTY_TWO_MILIONS_RANGE * 0.25;
      taxTotal += EIGHTEEN_MILIONS_RANGE * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterTax > THIRTY_TWO_MILIONS_RANGE && salaryAfterTax <= FIFTY_TWO_MILIONS_RANGE) {
      taxTotal += (salaryAfterTax - THIRTY_TWO_MILIONS_RANGE) * 0.25;
      taxTotal += EIGHTEEN_MILIONS_RANGE * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterTax > EIGHTEEN_MILIONS_RANGE && salaryAfterTax <= THIRTY_TWO_MILIONS_RANGE) {
      taxTotal += (salaryAfterTax - EIGHTEEN_MILIONS_RANGE) * 0.20;
      taxTotal += TEN_MILIONS_RANGE * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterTax > TEN_MILIONS_RANGE && salaryAfterTax <= EIGHTEEN_MILIONS_RANGE) {
      taxTotal += (salaryAfterTax - TEN_MILIONS_RANGE) * 0.15;
      taxTotal += FIVE_MILIONS_RANGE * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    if (salaryAfterTax > FIVE_MILIONS_RANGE && salaryAfterTax <= TEN_MILIONS_RANGE) {
      taxTotal += (salaryAfterTax - FIVE_MILIONS_RANGE) * 0.10;
      taxTotal += FIVE_MILIONS_RANGE * 0.05;
    }

    return taxTotal;
  }


  private double calcTNCT_N2G(double netSalary, int numberOfDependence)
  {
    double tnctAfterGiamTru = netSalary - GIAM_TRU_GIA_CANH_BAN_THAN - (numberOfDependence * GIA_CANH_PHU_THUOC);
    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_6) {
      return (tnctAfterGiamTru - 9850000) / N2G_UNDER_TAX_CONST_7;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_5 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_6) {
      return (tnctAfterGiamTru - 5850000) / N2G_UNDER_TAX_CONST_6;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_4 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_5) {
      return (tnctAfterGiamTru - 3250000) / N2G_UNDER_TAX_CONST_5;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_3 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_4) {
      return (tnctAfterGiamTru - 1650000) / N2G_UNDER_TAX_CONST_4;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_2 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_3) {
      return (tnctAfterGiamTru - 750000) / N2G_UNDER_TAX_CONST_3;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_1 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_2) {
      return (tnctAfterGiamTru - 250000) / N2G_UNDER_TAX_CONST_2;
    }

    if (tnctAfterGiamTru < N2G_UNDER_TAX_RANGE_1) {
      return tnctAfterGiamTru / N2G_UNDER_TAX_CONST_1;
    }
    return tnctAfterGiamTru;
  }
}
