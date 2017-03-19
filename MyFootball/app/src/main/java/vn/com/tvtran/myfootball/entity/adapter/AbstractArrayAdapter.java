package vn.com.tvtran.myfootball.entity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tvtran on 2/3/2017.
 * @author tvtran
 */

public class AbstractArrayAdapter<T extends Serializable> extends ArrayAdapter<T> {

    protected LayoutInflater inflater;
    protected List<T> objects;

    public AbstractArrayAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
        this.inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

}
