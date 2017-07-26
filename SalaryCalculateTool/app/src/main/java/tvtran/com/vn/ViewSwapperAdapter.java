package tvtran.com.vn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import tvtran.com.vn.adapter.FragmentStateAdapter;
import tvtran.com.vn.salarycalculatetool.CalcFragment;
import tvtran.com.vn.salarycalculatetool.QAndAFragment;
import tvtran.com.vn.salarycalculatetool.RefFragment;


public class ViewSwapperAdapter extends FragmentStateAdapter
{

    private static final int INDEX_CAL = 0;
    private static final int INDEX_REF = 1;
    private static final int INDEX_ASK = 2;

    public ViewSwapperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_CAL:
              return new CalcFragment();
            case INDEX_REF:
              return new RefFragment();
            case INDEX_ASK:
              return new QAndAFragment();
//                return ImageFragment.newInstance(R.drawable.values);
                //TestFragment fragment = new TestFragment();
//                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
