package app.xandone.com.yvertical;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * Created by xandone on 2017/6/5.
 */

public class YverticalView extends FrameLayout {
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
    private int mPosition = 0;

    private BaseYverticalAdapter mAdapter;

    public YverticalView(Context context) {
        this(context, null);
    }

    public YverticalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YverticalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        }
        mValueAnimator.setDuration(mDuration);
        mValueAnimator.setInterpolator(new LinearInterpolator());
    }

    public void setAdapter(BaseYverticalAdapter baseYverticalAdapter) {
        mAdapter = baseYverticalAdapter;
    }

    public void startScroll() {
        initView();
    }

    public void stopScroll() {
        mValueAnimator.cancel();
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
            FrameLayout.LayoutParams layoutParams = (LayoutParams) mSecondView.getLayoutParams();
            layoutParams.setMargins(0, mHeight, 0, 0);
            addView(mFirstview);
            addView(mSecondView);
            initAnim();
            postDelayed(animRunnable, mDuration);
        }
        return this;
    }

    public YverticalView initAnim() {
        mAdapter.bindView(mPosition % mAdapter.getCount(), mFirstview);
        mAdapter.bindView((mPosition + 1) % mAdapter.getCount(), mSecondView);
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
            mFirstview.setY(-mAnimValue * mHeight);
            mSecondView.setY(mHeight - mAnimValue * mHeight);
        }
    }

    public void resetAnim() {
        initAnim();
    }

    private AnimRunnable animRunnable = new AnimRunnable();

    private class AnimRunnable implements Runnable {

        @Override
        public void run() {
            mPosition++;
            mValueAnimator.cancel();
            initAnim();
            postDelayed(animRunnable, mDuration);
        }
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
