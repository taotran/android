package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tvtran.com.vn.entity.ConfigObject;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/21/2017
 */
public class ConfigFragment extends DialogFragment
{

  public ConfigFragment() {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_config, container, false);
  }
}
