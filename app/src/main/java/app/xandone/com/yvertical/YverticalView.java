package app.xandone.com.yvertical;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by xandone on 2017/6/5.
 */

public class YverticalView extends LinearLayout {
    public static final int STATE_VERTICAL = 0x01;
    public static final int STATE_HORIZONTAL = 0x02;

    private int corrent_state;

    private BaseYverticalAdapter mBaseYverticalAdapter;

    public YverticalView(Context context) {
        this(context, null);
    }

    public YverticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YverticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(BaseYverticalAdapter baseYverticalAdapter) {
        mBaseYverticalAdapter = baseYverticalAdapter;
    }

    public void startScroll() {

    }

    public void stopScroll() {

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }
}
