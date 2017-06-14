package app.xandone.com.yvertical;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

/**
 * Created by xandone on 2017/6/5.
 */

public class YverticalView extends LinearLayout {
    public static final int STATE_VERTICAL = 0x01;//垂直滚动
    public static final int STATE_HORIZONTAL = 0x02;//水平滚动
    public static final int STATE_NO_SCROLL = 0x03;//停止滚动
    public static final int STATE_PAUSE = 0x04;//暂停滚动s

    private int mCorrentState;
    private View mFirstview, mSecondView;
    private int mWidth, mHeight;
    private ValueAnimator mValueAnimator;
    private int mDuration = 2000;
    private float mAnimValue;

    private BaseYverticalAdapter mAdapter;

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
        mAdapter = baseYverticalAdapter;
    }

    public void startScroll() {
        initView();
    }

    public void stopScroll() {

    }

    public YverticalView initView() {
        if (mAdapter == null) {
            Log.d("xandone", "检查适配器");
            return this;
        }
        if (mAdapter.getCount() == 0) {
            mCorrentState = STATE_NO_SCROLL;
            return this;
        }
        if (mAdapter.getCount() == 1) {
            mCorrentState = STATE_NO_SCROLL;
            mFirstview = mAdapter.getLayout(this);
            addView(mFirstview);
        } else if (mAdapter.getCount() > 1) {
            mCorrentState = STATE_VERTICAL;
            mFirstview = mAdapter.getLayout(this);
            mSecondView = mAdapter.getLayout(this);
            addView(mFirstview);
            addView(mSecondView);
            initAnim();
        }
        return this;
    }

    public YverticalView initAnim() {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        }
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                scrollAnim();
                invalidate();
            }
        });
        mValueAnimator.start();
        return this;
    }

    public void scrollAnim() {
        if (mCorrentState == STATE_VERTICAL) {
            Log.d("xandone", "垂直滚动");
            mFirstview.setY(mAnimValue * mHeight);
        }
    }

    public void resetAnim() {
        initAnim();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }
}
