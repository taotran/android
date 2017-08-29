package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import tvtran.com.vn.entity.QuestionAndAnswer;
import tvtran.com.vn.salarycalculatetool.R;

import java.util.List;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/29/2017
 */
public class QuestionAndAnswerArrayAdapter extends ArrayAdapter
{
  private List<QuestionAndAnswer> objects;

  public QuestionAndAnswerArrayAdapter(Context context, int resourceId, List<QuestionAndAnswer> questionAndAnswers)
  {
    super(context, resourceId);
    this.objects = questionAndAnswers;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_qanda_item, null);
      viewHolder = new ViewHolder();
      convertView.setTag(viewHolder);
    }
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.questionTextView = (TextView) convertView.findViewById(R.id.questionTextView);
    viewHolder.questionTextView.setText(objects.get(position).getQuestion());

    return convertView;
  }



  static class ViewHolder
  {
    private TextView questionTextView;
  }
}
