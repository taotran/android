package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import tvtran.com.vn.entity.ConfigObject;
import tvtran.com.vn.utils.CurrencyTextWatcher;
import tvtran.com.vn.utils.Utils;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/21/2017
 */
public class ConfigFragment extends DialogFragment implements View.OnClickListener
{

  private ConfigObject configObject;
  private OnDialogFinish dialogFinish;
  private boolean isSaved;

  public ConfigFragment()
  {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fragment_config, container, false);
    configObject = (ConfigObject) getArguments().getSerializable("configObject");
    if (configObject == null) {
      configObject = new ConfigObject();
    }
    final EditText confCurrRate = ((EditText) view.findViewById(R.id.editTextConfCurrRate));
    confCurrRate.setText(String.valueOf(configObject.getCurrRate()));

    final EditText confBaseSal = ((EditText) view.findViewById(R.id.editTextConfBaseSal));
    confBaseSal.setText(Utils.formattedDouble(configObject.getBaseSalary()));

    final EditText confBHXH = ((EditText) view.findViewById(R.id.editTextBHXH));
    confBHXH.setText(String.valueOf(configObject.getRateBHXH()));

    final EditText confBHYT = ((EditText) view.findViewById(R.id.editTextBHYT));
    confBHYT.setText(String.valueOf(configObject.getRateBHYT()));

    final EditText confBHTN = ((EditText) view.findViewById(R.id.editTextBHTN));
    confBHTN.setText(String.valueOf(configObject.getRateBHTN()));

    Button saveBtn = (Button) view.findViewById(R.id.buttonSaveConf);
    saveBtn.setOnClickListener(this);
    return view;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onResume()
  {
    final EditText baseSalaryEditText = (EditText) getView().findViewById(R.id.editTextConfBaseSal);
    baseSalaryEditText.addTextChangedListener(new CurrencyTextWatcher());

    super.onResume();
  }

  @Override
  public void onClick(View v)
  {
    isSaved = true;
    this.dismiss();

  }

  @Override
  public void onDestroyView()
  {
    if (isSaved) {
      @SuppressWarnings("all") final String currRateString = ((EditText) getView().findViewById(R.id.editTextConfCurrRate)).getText().toString().replaceAll(",", "");
      final String baseSalaryString = ((EditText) getView().findViewById(R.id.editTextConfBaseSal)).getText().toString().replaceAll(",", "");
      final String bhxhString = ((EditText) getView().findViewById(R.id.editTextBHXH)).getText().toString();
      final String bhytString = ((EditText) getView().findViewById(R.id.editTextBHYT)).getText().toString();
      final String bhtnString = ((EditText) getView().findViewById(R.id.editTextBHTN)).getText().toString();

      final double currencyRate = Double.valueOf(currRateString);
      final double baseSalary = Double.valueOf(baseSalaryString);
      final double bhxh = Double.valueOf(bhxhString);
      final double bhyt = Double.valueOf(bhytString);
      final double bhtn = Double.valueOf(bhtnString);


      configObject = new ConfigObject(currencyRate, baseSalary, bhxh, bhyt, bhtn);
      if (dialogFinish != null) {
        dialogFinish.onFinish(configObject);
      }
    }
    super.onDestroyView();
  }

  public void setDialogFinish(OnDialogFinish dialogFinish)
  {
    this.dialogFinish = dialogFinish;
  }

  public interface OnDialogFinish
  {
    void onFinish(ConfigObject configObject);
  }
}
