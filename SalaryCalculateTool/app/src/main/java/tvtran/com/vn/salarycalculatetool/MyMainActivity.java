package tvtran.com.vn.salarycalculatetool;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import tvtran.com.vn.ViewSwapperAdapter;
import tvtran.com.vn.adapter.ExpandableDetailAdapter;
import tvtran.com.vn.entity.Detail;
import tvtran.com.vn.entity.DetailGroupHeader;
import tvtran.com.vn.service.GrossToNetCalculator;
import tvtran.com.vn.service.ICalculator;
import tvtran.com.vn.service.NetToGrossCalculator;
import tvtran.com.vn.utils.Utils;
import tvtran.com.vn.view.AdaptableBottomNavigationView;
import tvtran.com.vn.view.ViewSwapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/24/2017
 */
public class MyMainActivity extends AppCompatActivity
{

  private AdaptableBottomNavigationView bottomNavigationView;
  private ViewSwapperAdapter viewSwapperAdapter;
  private ViewSwapper viewSwapper;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mymain);


    bottomNavigationView = (AdaptableBottomNavigationView)
        findViewById(R.id.view_bottom_navigation);
    viewSwapper = (ViewSwapper) findViewById(R.id.view_swapper);
    viewSwapperAdapter = new ViewSwapperAdapter(getSupportFragmentManager());

    viewSwapper.setAdapter(viewSwapperAdapter);
    bottomNavigationView.setupWithViewSwapper(viewSwapper);
  }

  public void onCalculateClick(View view)
  {
    final String salaryString = ((EditText) findViewById(R.id.salaryEditText)).getText().toString();
    final String noOfDependence = ((Spinner) findViewById(R.id.dependenceSpinner)).getSelectedItem().toString();


    if (salaryString.trim().isEmpty()) {

      AlertDialog.Builder dialog;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        dialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
      }
      else {
        dialog = new AlertDialog.Builder(this);
      }
      dialog
          .setTitle("Lỗi!")
          .setMessage("Vui lòng nhập lương trước khi tính!")
          .setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
              ((EditText) findViewById(R.id.salaryEditText)).requestFocus();
            }
          });
      dialog.show();
      return;
    }

    final CheckBox net2GrossCheckBox = (CheckBox) findViewById(R.id.checkBoxNet2Gross);
    Utils.hideSoftKeyboard(this);


    final List<DetailGroupHeader> headers = new ArrayList<>();
    DetailGroupHeader detailGroupHeader = new DetailGroupHeader(1, "Diễn giải chi tiết (VND)");
    DetailGroupHeader detailGroupHeader1 = new DetailGroupHeader(2, "(*) Chi tiết thuế thu nhập cá nhân (VND)");
    DetailGroupHeader detailGroupHeader2 = new DetailGroupHeader(3, "Người sử dụng lao động trả (VND)");

    headers.add(detailGroupHeader);
    headers.add(detailGroupHeader1);
    headers.add(detailGroupHeader2);

    final Map<Integer, List<Detail>> details = Utils.initDetailsMap();
    final ICalculator calculator;
    final Double finalResult;

    if (net2GrossCheckBox.isChecked()) {
      calculator = new NetToGrossCalculator(Double.parseDouble(salaryString), Integer.parseInt(noOfDependence), details);

    }
    else {
      calculator = new GrossToNetCalculator(Double.parseDouble(salaryString), Integer.parseInt(noOfDependence), details);
    }
    finalResult = calculator.calculate();

    final ExpandableDetailAdapter detailAdapter = new ExpandableDetailAdapter(this, headers, details);

    final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detailExpandableListView);

    expandableListView.setAdapter(detailAdapter);

    final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
    resultTextView.setVisibility(View.VISIBLE);
    resultTextView.setText(Utils.formattedDouble(finalResult));

    final TextView finalResultTextView = (TextView) findViewById(R.id.finalResultTextView);
    finalResultTextView.setVisibility(View.VISIBLE);
  }
}
