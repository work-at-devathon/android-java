package com.parse.starter;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.Random;

public class MyCardContainer extends AdapterView<ListAdapter> {
    public static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private static final double DISORDERED_MAX_ROTATION_RADIANS = 0.04908738521234052D;
    private int mNumberOfCards = -1;
    private final DataSetObserver mDataSetObserver = new DataSetObserver() {
        public void onChanged() {
            super.onChanged();
            MyCardContainer.this.clearStack();
            MyCardContainer.this.ensureFull();
        }

        public void onInvalidated() {
            super.onInvalidated();
            MyCardContainer.this.clearStack();
        }
    };
    private final Random mRandom = new Random();
    private final Rect boundsRect = new Rect();
    private final Rect childRect = new Rect();
    private final Matrix mMatrix = new Matrix();
    private int mMaxVisible = 10;
    private GestureDetector mGestureDetector;
    private int mFlingSlop;
    private Orientations.Orientation mOrientation;
    private ListAdapter mListAdapter;
    private float mLastTouchX;
    private float mLastTouchY;
    private View mTopCard;
    private int mTouchSlop;
    private int mGravity;
    private int mNextAdapterPosition;
    private boolean mDragging;

    public MyCardContainer(Context context) {
        super(context);
        this.setOrientation(Orientations.Orientation.Disordered);
        this.setGravity(17);
        this.init();
    }

    public MyCardContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initFromXml(attrs);
        this.init();
    }

    public MyCardContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initFromXml(attrs);
        this.init();
    }

    private void init() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.getContext());
        this.mFlingSlop = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mGestureDetector = new GestureDetector(this.getContext(), new MyCardContainer.GestureListener());
    }

    private void initFromXml(AttributeSet attr) {
        TypedArray a = this.getContext().obtainStyledAttributes(attr, R.styleable.CardContainer);
        this.setGravity(a.getInteger(0, 17));
        int orientation = a.getInteger(1, 1);
        this.setOrientation(Orientations.Orientation.fromIndex(orientation));
        a.recycle();
    }

    public ListAdapter getAdapter() {
        return this.mListAdapter;
    }

    public void setAdapter(ListAdapter adapter) {
        if (this.mListAdapter != null) {
            this.mListAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }

        this.clearStack();
        this.mTopCard = null;
        this.mListAdapter = adapter;
        this.mNextAdapterPosition = 0;
        adapter.registerDataSetObserver(this.mDataSetObserver);
        this.ensureFull();
        if (this.getChildCount() != 0) {
            this.mTopCard = this.getChildAt(this.getChildCount() - 1);
            this.mTopCard.setLayerType(2, (Paint) null);
        }

        this.mNumberOfCards = this.getAdapter().getCount();
        this.requestLayout();
    }

    private void ensureFull() {
        while (this.mNextAdapterPosition < this.mListAdapter.getCount() && this.getChildCount() < this.mMaxVisible) {
            View view = this.mListAdapter.getView(this.mNextAdapterPosition, (View) null, this);
            view.setLayerType(1, (Paint) null);
            if (this.mOrientation == Orientations.Orientation.Disordered) {
                view.setRotation(this.getDisorderedRotation());
            }

            this.addViewInLayout(view, 0, new MyCardContainer.LayoutParams(-2, -2, this.mListAdapter.getItemViewType(this.mNextAdapterPosition)), false);
            this.requestLayout();
            ++this.mNextAdapterPosition;
        }

    }

    private void clearStack() {
        this.removeAllViewsInLayout();
        this.mNextAdapterPosition = 0;
        this.mTopCard = null;
    }

    public Orientations.Orientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(Orientations.Orientation orientation) {
        if (orientation == null) {
            throw new NullPointerException("Orientation may not be null");
        } else {
            if (this.mOrientation != orientation) {
                this.mOrientation = orientation;
                int i;
                View child;
                if (orientation == Orientations.Orientation.Disordered) {
                    for (i = 0; i < this.getChildCount(); ++i) {
                        child = this.getChildAt(i);
                        child.setRotation(this.getDisorderedRotation());
                    }
                } else {
                    for (i = 0; i < this.getChildCount(); ++i) {
                        child = this.getChildAt(i);
                        child.setRotation(0.0F);
                    }
                }

                this.requestLayout();
            }

        }
    }

    private float getDisorderedRotation() {
        return (float) Math.toDegrees(this.mRandom.nextGaussian() * 0.04908738521234052D);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int requestedWidth = this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
        int requestedHeight = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
        int childWidth;
        int childHeight;
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        if (this.mOrientation == Orientations.Orientation.Disordered) {
            if (requestedWidth >= requestedHeight) {
                childWidthMeasureSpec = requestedHeight;
                childHeightMeasureSpec = requestedWidth;
            } else {
                childWidthMeasureSpec = requestedWidth;
                childHeightMeasureSpec = requestedHeight;
            }

            childWidth = (int) (((double) childWidthMeasureSpec * Math.cos(0.04908738521234052D) - (double) childHeightMeasureSpec * Math.sin(0.04908738521234052D)) / Math.cos(0.09817477042468103D));
            childHeight = (int) (((double) childHeightMeasureSpec * Math.cos(0.04908738521234052D) - (double) childWidthMeasureSpec * Math.sin(0.04908738521234052D)) / Math.cos(0.09817477042468103D));
        } else {
            childWidth = requestedWidth;
            childHeight = requestedHeight;
        }

        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST);
        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST);

        for (int i = 0; i < this.getChildCount(); ++i) {
            View child = this.getChildAt(i);

            assert child != null;

            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        for (int i = 0; i < this.getChildCount(); ++i) {
            this.boundsRect.set(0, 0, this.getWidth(), this.getHeight());
            View view = this.getChildAt(i);
            int w = view.getMeasuredWidth();
            int h = view.getMeasuredHeight();
            Gravity.apply(this.mGravity, w, h, this.boundsRect, this.childRect);
            view.layout(this.childRect.left, this.childRect.top, this.childRect.right, this.childRect.bottom);
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mTopCard == null) {
            return false;
        } else if (this.mGestureDetector.onTouchEvent(event)) {
            return true;
        } else {
            Log.d("Touch Event", MotionEvent.actionToString(event.getActionMasked()) + " ");
            int pointerIndex;
            float x;
            float y;
            switch (event.getActionMasked()) {
                case 0:
                    this.mTopCard.getHitRect(this.childRect);
                    pointerIndex = event.getActionIndex();
                    x = event.getX(pointerIndex);
                    y = event.getY(pointerIndex);
                    if (!this.childRect.contains((int) x, (int) y)) {
                        return false;
                    }

                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    this.mActivePointerId = event.getPointerId(pointerIndex);
                    float[] points = new float[]{x - (float) this.mTopCard.getLeft(), y - (float) this.mTopCard.getTop()};
                    this.mTopCard.getMatrix().invert(this.mMatrix);
                    this.mMatrix.mapPoints(points);
                    this.mTopCard.setPivotX(points[0]);
                    this.mTopCard.setPivotY(points[1]);
                    break;
                case 1:
                case 3:
                    if (!this.mDragging) {
                        return true;
                    }

                    this.mDragging = false;
                    this.mActivePointerId = -1;
                    ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(this.mTopCard, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("translationX", new float[]{0.0F}), PropertyValuesHolder.ofFloat("translationY", new float[]{0.0F}), PropertyValuesHolder.ofFloat("rotation", new float[]{(float) Math.toDegrees(this.mRandom.nextGaussian() * 0.04908738521234052D)}), PropertyValuesHolder.ofFloat("pivotX", new float[]{(float) this.mTopCard.getWidth() / 2.0F}), PropertyValuesHolder.ofFloat("pivotY", new float[]{(float) this.mTopCard.getHeight() / 2.0F})}).setDuration(250L);
                    animator.setInterpolator(new AccelerateInterpolator());
                    animator.start();
                    break;
                case 2:
                    pointerIndex = event.findPointerIndex(this.mActivePointerId);
                    x = event.getX(pointerIndex);
                    y = event.getY(pointerIndex);
                    float dx = x - this.mLastTouchX;
                    float dy = y - this.mLastTouchY;
                    if (Math.abs(dx) > (float) this.mTouchSlop || Math.abs(dy) > (float) this.mTouchSlop) {
                        this.mDragging = true;
                    }

                    if (!this.mDragging) {
                        return true;
                    }

                    this.mTopCard.setTranslationX(this.mTopCard.getTranslationX() + dx);
                    this.mTopCard.setTranslationY(this.mTopCard.getTranslationY() + dy);
                    this.mTopCard.setRotation(40.0F * this.mTopCard.getTranslationX() / ((float) this.getWidth() / 2.0F));
                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                case 4:
                case 5:
                default:
                    break;
                case 6:
                    pointerIndex = event.getActionIndex();
                    int pointerId = event.getPointerId(pointerIndex);
                    if (pointerId == this.mActivePointerId) {
                        int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                        this.mLastTouchX = event.getX(newPointerIndex);
                        this.mLastTouchY = event.getY(newPointerIndex);
                        this.mActivePointerId = event.getPointerId(newPointerIndex);
                    }
            }

            return true;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.mTopCard == null) {
            return false;
        } else if (this.mGestureDetector.onTouchEvent(event)) {
            return true;
        } else {
            int pointerIndex;
            float x;
            float y;
            switch (event.getActionMasked()) {
                case 0:
                    this.mTopCard.getHitRect(this.childRect);
                    MyCardModel cardModel = (MyCardModel) this.getAdapter().getItem(this.getChildCount() - 1);
                    if (cardModel.getOnClickListener() != null) {
                        cardModel.getOnClickListener().OnClickListener();
                    }

                    pointerIndex = event.getActionIndex();
                    x = event.getX(pointerIndex);
                    y = event.getY(pointerIndex);
                    if (!this.childRect.contains((int) x, (int) y)) {
                        return false;
                    }

                    this.mLastTouchX = x;
                    this.mLastTouchY = y;
                    this.mActivePointerId = event.getPointerId(pointerIndex);
                    break;
                case 2:
                    pointerIndex = event.findPointerIndex(this.mActivePointerId);
                    x = event.getX(pointerIndex);
                    y = event.getY(pointerIndex);
                    if (Math.abs(x - this.mLastTouchX) > (float) this.mTouchSlop || Math.abs(y - this.mLastTouchY) > (float) this.mTouchSlop) {
                        float[] points = new float[]{x - (float) this.mTopCard.getLeft(), y - (float) this.mTopCard.getTop()};
                        this.mTopCard.getMatrix().invert(this.mMatrix);
                        this.mMatrix.mapPoints(points);
                        this.mTopCard.setPivotX(points[0]);
                        this.mTopCard.setPivotY(points[1]);
                        return true;
                    }
            }

            return false;
        }
    }

    public View getSelectedView() {
        throw new UnsupportedOperationException();
    }

    public void setSelection(int position) {
        throw new UnsupportedOperationException();
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("Fling", "Fling with " + velocityX + ", " + velocityY);
            final View topCard = MyCardContainer.this.mTopCard;
            float dx = e2.getX() - e1.getX();
            if (Math.abs(dx) > (float) MyCardContainer.this.mTouchSlop && Math.abs(velocityX) > Math.abs(velocityY) && Math.abs(velocityX) > (float) (MyCardContainer.this.mFlingSlop * 3)) {
                float targetX = topCard.getX();
                float targetY = topCard.getY();
                long duration = 0L;
                MyCardContainer.this.boundsRect.set(0 - topCard.getWidth() - 100, 0 - topCard.getHeight() - 100, MyCardContainer.this.getWidth() + 100, MyCardContainer.this.getHeight() + 100);

                while (MyCardContainer.this.boundsRect.contains((int) targetX, (int) targetY)) {
                    targetX += velocityX / 10.0F;
                    targetY += velocityY / 10.0F;
                    duration += 100L;
                }

                duration = Math.min(500L, duration);
                MyCardContainer.this.mTopCard = MyCardContainer.this.getChildAt(MyCardContainer.this.getChildCount() - 2);
                MyCardModel cardModel = (MyCardModel) MyCardContainer.this.getAdapter().getItem(MyCardContainer.this.getChildCount() - 1);
                if (MyCardContainer.this.mTopCard != null) {
                    MyCardContainer.this.mTopCard.setLayerType(2, (Paint) null);
                }

                if (cardModel.getOnCardDimissedListener() != null) {
                    if (targetX > 0.0F) {
                        cardModel.getOnCardDimissedListener().onDislike();
                    } else {
                        cardModel.getOnCardDimissedListener().onLike();
                    }
                }

                topCard.animate().setDuration(duration).alpha(0.75F).setInterpolator(new LinearInterpolator()).x(targetX).y(targetY).rotation(Math.copySign(45.0F, velocityX)).setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        MyCardContainer.this.removeViewInLayout(topCard);
                        MyCardContainer.this.ensureFull();
                    }

                    public void onAnimationCancel(Animator animation) {
                        this.onAnimationEnd(animation);
                    }
                });
                return true;
            } else {
                return false;
            }
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        int viewType;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(int w, int h, int viewType) {
            super(w, h);
            this.viewType = viewType;
        }
    }
}
