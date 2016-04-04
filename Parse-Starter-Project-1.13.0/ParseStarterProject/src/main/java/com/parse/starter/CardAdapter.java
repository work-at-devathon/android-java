package com.parse.starter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;

public abstract class CardAdapter extends BaseCardAdapter {

    private final Context context;
    final Object mLock = new Object();
    ArrayList<MyCardModel> mData;

    public CardAdapter(Context context) {
        this.context = context;
        this.mData = new ArrayList<>();
    }

//    public CardAdapter(Context context, ArrayList< MyCardModel> items) {
//        this.context = context;
//        this.mData = new ArrayList(items);
//    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.getCardModel(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) this.getItem(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FrameLayout wrapper = (FrameLayout) view;
        FrameLayout innerWrapper;
        View cardView;
        if (wrapper == null) {
            wrapper = new FrameLayout(this.context);
            wrapper.setBackgroundResource(R.drawable.card_bg);
            if (this.shouldFillCardBackground()) {
                innerWrapper = new FrameLayout(this.context);
                innerWrapper.setBackgroundColor(this.context.getResources().getColor(R.color.card_bg));
                wrapper.addView(innerWrapper);
            } else {
                innerWrapper = wrapper;
            }

            cardView = this.getCardView(i, this.getCardModel(i), (View) null, viewGroup);
            innerWrapper.addView(cardView);
        } else {
            if (this.shouldFillCardBackground()) {
                innerWrapper = (FrameLayout) wrapper.getChildAt(0);
            } else {
                innerWrapper = wrapper;
            }

            cardView = innerWrapper.getChildAt(0);
            View convertedCardView = this.getCardView(i, this.getCardModel(i), cardView, viewGroup);
            if (convertedCardView != cardView) {
                wrapper.removeView(cardView);
                wrapper.addView(convertedCardView);
            }
        }

        return wrapper;
    }

    protected abstract View getCardView(int var1, MyCardModel var2, View var3, ViewGroup var4);

    public boolean shouldFillCardBackground() {
        return true;
    }

    public MyCardModel getCardModel(int position) {
        Object var2 = this.mLock;
        synchronized (this.mLock) {
            return (MyCardModel) this.mData.get(this.mData.size() - 1 - position);
        }
        // this.notifyDataSetChanged();
    }

    public void add(MyCardModel item) {
        Object var2 = this.mLock;
        synchronized (this.mLock) {
            this.mData.add(item);
        }

        this.notifyDataSetChanged();
    }
//    public MyCardModel pop() {
//        Object var2 = this.mLock;
//        MyCardModel model;
//        synchronized(this.mLock) {
//            model = (MyCardModel)this.mData.remove(this.mData.size() - 1);
//        }
//
//        this.notifyDataSetChanged();
//        return model;
//    }
}
