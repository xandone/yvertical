package app.xandone.com.yvertical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created by xandone on 2017/6/14.
 */

public class MyAdapter extends BaseYverticalAdapter<String> {
    private List<String> list;
    private Context mContext;

    public MyAdapter(Context context, List list) {
        super(list);
        this.list = list;
        this.mContext = context;
    }

    @Override
    public View setLayout(YverticalView yverticalView) {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.item_first_layout, yverticalView, false);
        return view;
    }
}
