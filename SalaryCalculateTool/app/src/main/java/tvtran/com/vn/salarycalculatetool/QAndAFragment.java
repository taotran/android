package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import tvtran.com.vn.adapter.QuestionAndAnswerArrayAdapter;
import tvtran.com.vn.entity.QuestionAndAnswer;
import tvtran.com.vn.service.MySQLiteHelper;

import java.util.List;

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
    super.onResume();
    final MySQLiteHelper helper = new MySQLiteHelper(getContext());
    ListView listView = (ListView) getActivity().findViewById(R.id.qandaListView);
    final List<QuestionAndAnswer> questionAndAnswers = helper.getAllQuestionAndAnswers();
    listView.setAdapter(new QuestionAndAnswerArrayAdapter(getActivity(), questionAndAnswers));

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id)
      {
        FragmentManager fragmentManager = getFragmentManager();

        QAndADialogFragment dialogFragment = new QAndADialogFragment();
        Bundle args = new Bundle();

//    args.putParcelable("configObject", new ConfigObject());
        args.putSerializable("questionAndAnswer", questionAndAnswers.get(position));
        dialogFragment.setArguments(args);
        dialogFragment.show(fragmentManager, "QuestionAndAnswer");

      }
    });

  }
}
