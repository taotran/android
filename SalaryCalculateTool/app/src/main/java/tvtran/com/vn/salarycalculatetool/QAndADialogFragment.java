package tvtran.com.vn.salarycalculatetool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import tvtran.com.vn.entity.QuestionAndAnswer;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/30/2017
 */
public class QAndADialogFragment extends DialogFragment
{


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    final View view = inflater.inflate(R.layout.fragment_dialog_qanda, container, false);

    final TextView fullQuestion = (TextView)view.findViewById(R.id.textViewFQuestion);
    final TextView fullAnswer = (TextView) view.findViewById(R.id.textViewFullAnswer);

    final QuestionAndAnswer receivedQuestionAndAnswer = (QuestionAndAnswer) getArguments().getSerializable("questionAndAnswer");

    fullQuestion.setText(receivedQuestionAndAnswer.getQuestion());
    fullAnswer.setText(receivedQuestionAndAnswer.getAnswer());

    return view;
  }
}
