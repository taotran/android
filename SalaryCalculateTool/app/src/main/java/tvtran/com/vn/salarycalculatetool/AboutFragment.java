package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/30/2017
 */
public class AboutFragment extends Fragment
{
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.fragment_about, container, false);
  }
}
