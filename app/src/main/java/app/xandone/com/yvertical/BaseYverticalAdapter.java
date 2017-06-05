package app.xandone.com.yvertical;

import android.view.View;

import java.util.List;

/**
 * Created by xandone on 2017/6/5.
 */

public abstract class BaseYverticalAdapter<T> {
    private List<T> mList;

    public BaseYverticalAdapter(List list) {
        this.mList = list;
    }

    public abstract View initLayout(int layoutId);

    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    public void notifyChanged() {

    }


    public class DataCahange implements DataChangedInterface {

        @Override
        public void onChanged() {

        }
    }

}
