package tvtran.com.vn.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/31/2017
 */
public class MyScrollView extends ScrollView
{
  public MyScrollView(Context context)
  {
    super(context);
  }

  public MyScrollView(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }

  public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    heightMeasureSpec = MeasureSpec.makeMeasureSpec(1000, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }
}
