package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import tvtran.com.vn.adapter.QuestionAndAnswerArrayAdapter;
import tvtran.com.vn.service.MySQLiteHelper;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/26/2017
 */
public class QAndAFragment extends Fragment
{

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {

    final View view = inflater.inflate(R.layout.fragment_qanda, container, false);

    return view;
  }

  @Override
  public void onResume()
  {
    final MySQLiteHelper helper = new MySQLiteHelper(getContext());
    ListView listView = (ListView) getView().findViewById(R.id.qandaListView);
    listView.setAdapter(new QuestionAndAnswerArrayAdapter(getContext(), R.layout.single_qanda_item, helper.getAllQuestionAndAnswers()));
    super.onResume();
  }
}
