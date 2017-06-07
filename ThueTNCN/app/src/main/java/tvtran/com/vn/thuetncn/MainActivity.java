package tvtran.com.vn.thuetncn;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import tvtran.com.vn.Utils;
import tvtran.com.vn.adapter.ExpandableDetailAdapter;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener
{

  private static final double BHXH = 0.08;
  private static final double BHYT = 0.015;
  private static final double BHTN = 0.01;

  //@formatter:off
  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;

  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000;
  private static final double GIA_CANH_PHU_THUOC          = 3600000;





  //@formatter:on

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_target_ranges);

    LinearLayout toolbarRow = (LinearLayout) toolbar.findViewById(R.id.target_ranges_layout_main);

    toolbarRow.setLayoutParams(new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

    setSupportActionBar(toolbar);


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

//    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//    navigationView.setNavigationItemSelectedListener(this);


  }

  @Override
  public void onBackPressed()
  {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    }
    else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item)
  {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {

    }
    else if (id == R.id.nav_gallery) {
      SalaryCalcFragment salaryCalcFragment = new SalaryCalcFragment();
      FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

      fragmentTransaction.replace(R.id.fragment_container, salaryCalcFragment);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
    }
    else if (id == R.id.nav_slideshow) {

    }
    else if (id == R.id.nav_manage) {

    }
    else if (id == R.id.nav_share) {

    }
    else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void onCalculateClick(View view)
  {
    System.out.println("You just click calculate");
    Utils.hideSoftKeyboard(this);
//    final ListView detailListView = (ListView) findViewById(R.id.detailListView);
//
//    detailListView.setAdapter(new DetailAdapter(MainActivity.this, Utils.init()));
//

    ///
    final List<DetailGroupHeader> headers = new ArrayList<>();
    DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
    DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
    DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");

    headers.add(detailGroupHeader);
    headers.add(detailGroupHeader1);
    headers.add(detailGroupHeader2);

    final Map<DetailGroupHeader, List<Detail>> details = new HashMap<>();

    final List<Detail> detailList = new ArrayList<>();

    detailList.add(new Detail("Lương GROSS", "24,600,000", "", 0));
    detailList.add(new Detail("Bảo hiểm xã hội (8%)", "24,600,000", "", 1));
    detailList.add(new Detail("Bảo hiểm y tế (1.5%)", "24,600,000", "", 2));
    detailList.add(new Detail("Bảo hiểm thất nghiệp (1% - lương tối thiểu vùng)", "24,600,000", "", 3));
    detailList.add(new Detail("Thu nhập trước thuế", "24,600,000", "", 4));
    detailList.add(new Detail("Giảm trừ gia cảnh bản thân", "24,600,000", "", 5));
    detailList.add(new Detail("Giảm trừ gia cảnh người phụ thuộc", "24,600,000", "", 6));
    detailList.add(new Detail("Thu nhập chịu thuế", "24,600,000", "", 7));
    detailList.add(new Detail("Thuế thu nhập cá nhân (*)", "24,600,000", "", 8));
    detailList.add(new Detail("Lương NET", "24,600,000", "", 9));

    details.put(detailGroupHeader, detailList);


    ////


    final ExpandableDetailAdapter detailAdapter = new ExpandableDetailAdapter(this, headers, details);

    final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detailListView);

    expandableListView.setAdapter(detailAdapter);

    new AlertDialog.Builder(this)
        .setTitle("Kết quả")
        .setMessage("Lương Net: " + String.format("%.0f", calculateFinalSalary()))
        .setCancelable(true)
        .show();


  }

  private void createSampleData()
  {


  }

  private double calculateFinalSalary()
  {
    double result = 0f;
    double salaryAfterInsurances = 0f;
    double salaryAfterDependencies = 0f;
//    double salaryAfterAppliedPersonalTax = 0f;

    final EditText salaryTextView = (EditText) findViewById(R.id.txtSalary);
    final EditText noOfDependenceTextView = (EditText) findViewById(R.id.txtNoDependence);

    final Double salary = new Double(salaryTextView.getText().toString());
    final Integer noDependencies = noOfDependenceTextView.getText() != null ? Integer.valueOf(noOfDependenceTextView.getText().toString()) : 0;

    salaryAfterInsurances = calcSalaryAfterAppliedInsurances(salary);
    if (noDependencies > 0) {
      salaryAfterDependencies = salaryAfterInsurances - GIAM_TRU_GIA_CANH_BAN_THAN - (noDependencies * GIA_CANH_PHU_THUOC);
    }
    else {
      salaryAfterDependencies = salaryAfterInsurances - GIAM_TRU_GIA_CANH_BAN_THAN;
    }

    result = salaryAfterInsurances - calcThueTNCNByRange(salaryAfterDependencies);
    return result;
  }

  private Double calcSalaryAfterAppliedInsurances(Double grossSalary)
  {
    final double appliedBHXH = grossSalary * BHXH;
    final double appliedBHYT = grossSalary * BHYT;
    final double appliedBHTN = grossSalary * BHTN;

    final double salaryBeforeTax = grossSalary - (appliedBHXH + appliedBHYT + appliedBHTN);

    return salaryBeforeTax;
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
}
