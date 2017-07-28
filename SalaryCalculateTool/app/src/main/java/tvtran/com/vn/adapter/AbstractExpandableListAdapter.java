package tvtran.com.vn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;
import tvtran.com.vn.entity.ExpandableGroupEntity;
import tvtran.com.vn.entity.IdentifiableEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by tvtran on 3/11/2017.
 *
 * @author tvtran
 */

public abstract class AbstractExpandableListAdapter<S extends ExpandableGroupEntity, T extends IdentifiableEntity>
    extends BaseExpandableListAdapter
{

  protected Context _context;
  private List<S> _listDataHeader; // header titles

  private Map<Integer, List<T>> _listDataChild;// child data in format of header title, child title

  protected LayoutInflater inflater;

  public AbstractExpandableListAdapter(Context context, List<S> listDataHeader, Map<Integer, List<T>> _listDataChild)
  {
    this._context = context;
    this._listDataHeader = listDataHeader;
    this._listDataChild = _listDataChild;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public int getGroupCount()
  {
    return _listDataHeader.size();
  }

  @Override
  public int getChildrenCount(int groupPosition)
  {
    return _listDataChild.get(_listDataHeader.get(groupPosition).getId()).size();
  }

  @Override
  public S getGroup(int groupPosition)
  {
    return _listDataHeader.get(groupPosition);
  }

  @Override
  public T getChild(int groupPosition, int childPosition)
  {
    return _listDataChild.get(_listDataHeader.get(groupPosition).getId()).get(childPosition);
  }

  @Override
  public long getGroupId(int groupPosition)
  {
    return groupPosition;
  }

  @Override
  public long getChildId(int groupPosition, int childPosition)
  {

    return _listDataChild.get(_listDataHeader.get(groupPosition).getId()).get(childPosition).getId();
  }

  @Override
  public boolean hasStableIds()
  {
    return true;
  }

}
