package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.tvtran.myfootball.entity.IMFEntity;

/**
 * Created by tvtran on 3/11/2017.
 *
 * @author tvtran
 */

public abstract class AbstractExpandableListAdapter<T extends IMFEntity, S extends ExpandableGroupEntity> extends BaseExpandableListAdapter {

    private Context _context;
    private List<S> _listDataHeader; // header titles
    // child data in format of header title, child title
    private Map<S, List<T>> _listDataChild;

    public AbstractExpandableListAdapter(Context context, List<S> listDataHeader, HashMap<S, List<T>> _listDataChild) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = _listDataChild;
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public Context getContext() {
        return _context;
    }

}
