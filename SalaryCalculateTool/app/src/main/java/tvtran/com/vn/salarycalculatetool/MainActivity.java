package tvtran.com.vn.salarycalculatetool;

//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import tvtran.com.vn.adapter.ExpandableDetailAdapter;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;
import tvtran.com.vn.service.GrossToNetCalculator;
import tvtran.com.vn.service.ICalculator;
import tvtran.com.vn.service.NetToGrossCalculator;
import tvtran.com.vn.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    fragmentTransaction.replace(R.id.fragment_container, new CalcFragment());
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();


  }

  @Override
  protected void onResume()
  {
    super.onResume();
  }


//  public void onGrossToNetClick(View view)
//  {
//    Utils.hideSoftKeyboard(this);
//
//    final String salaryString = ((EditText) findViewById(R.id.salaryEditText)).getText().toString();
//    final String noOfDependence = ((Spinner) findViewById(R.id.dependenceSpinner)).getSelectedItem().toString();
//
//    final List<DetailGroupHeader> headers = new ArrayList<>();
//    DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
//    DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
//    DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");
//
//    headers.add(detailGroupHeader);
//    headers.add(detailGroupHeader1);
//    headers.add(detailGroupHeader2);
//
//    final Map<Integer, List<Detail>> details = Utils.initDetailsMap();
//
//    ICalculator calculator = new GrossToNetCalculator(Double.parseDouble(salaryString), Integer.parseInt(noOfDependence), details);
//    final Double finalG2NResult = calculator.calculate();
//
//    final ExpandableDetailAdapter detailAdapter = new ExpandableDetailAdapter(this, headers, details);
//
//    final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detailExpandableListView);
//
//    expandableListView.setAdapter(detailAdapter);
//
//    final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
//    resultTextView.setVisibility(View.VISIBLE);
//    resultTextView.setText(Utils.formattedDouble(finalG2NResult));
//
//  }
//
//  public void onNetToGrossClick(View view)
//  {
//    Utils.hideSoftKeyboard(this);
//
//    final String salaryString = ((EditText) findViewById(R.id.salaryEditText)).getText().toString();
//    final String noOfDependence = ((Spinner) findViewById(R.id.dependenceSpinner)).getSelectedItem().toString();
//
//    final List<DetailGroupHeader> headers = new ArrayList<>();
//    DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
//    DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
//    DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");
//
//    headers.add(detailGroupHeader);
//    headers.add(detailGroupHeader1);
//    headers.add(detailGroupHeader2);
//
//    final Map<Integer, List<Detail>> details = Utils.initDetailsMap();
//
//    ICalculator calculator = new NetToGrossCalculator(Double.parseDouble(salaryString), Integer.parseInt(noOfDependence), details);
//    final Double finalN2GResult = calculator.calculate();
//
//    final ExpandableDetailAdapter detailAdapter = new ExpandableDetailAdapter(this, headers, details);
//
//    final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detailExpandableListView);
//
//    expandableListView.setAdapter(detailAdapter);
//
//    final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
//    resultTextView.setVisibility(View.VISIBLE);
//    resultTextView.setText(Utils.formattedDouble(finalN2GResult));
//  }

}
