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

  public QuestionAndAnswerArrayAdapter(Context context, List<QuestionAndAnswer> questionAndAnswers)
  {
    super(context, 0);
    this.objects = questionAndAnswers;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    final LayoutInflater inflater = LayoutInflater.from(getContext());
    final ViewHolder viewHolder;
    final QuestionAndAnswer obj = objects.get(position);
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.single_qanda_item, null);
      viewHolder = new ViewHolder();
      convertView.setTag(viewHolder);
    }
    else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    viewHolder.questionTextView = (TextView) convertView.findViewById(R.id.questionTextView);
    viewHolder.questionTextView.setText(obj.getId() + ". " + obj.getShortQuestion());

    return convertView;
  }


  @Override
  public int getCount()
  {
    return objects.size();
  }

  static class ViewHolder
  {
    private TextView questionTextView;
  }
}
