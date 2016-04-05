package com.parse.starter;


public class MyCardModel {

    protected String hotelname;
    protected String address;
    protected String rating;
    protected String imageUrl;

//    public String getHotelName() {
//        return this.hotelname;
//    }
//
//    public String getAddress() {
//        return this.address;
//    }
//    public String getRating() {
//        return this.rating;
//    }
//    public String getImageUrl() { return this.imageUrl;}

    private MyCardModel.OnCardDimissedListener mOnCardDimissedListener;
    private MyCardModel.OnClickListener mOnClickListener;


    public void setOnCardDimissedListener(MyCardModel.OnCardDimissedListener listener) {
        this.mOnCardDimissedListener = listener;
    }

    public MyCardModel.OnCardDimissedListener getOnCardDimissedListener() {
        return this.mOnCardDimissedListener;
    }

    public void setOnClickListener(MyCardModel.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public MyCardModel.OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    public interface OnClickListener {
        void OnClickListener();
    }

    public interface OnCardDimissedListener {
        void onLike();

        void onDislike();
    }
    private boolean isLike = false;
    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean isLike) {
        this.isLike = isLike;
    }
//    private Drawable cardImageDrawable;
//    private Drawable cardLikeImageDrawable;
//    private Drawable cardDislikeImageDrawable;
//
//
//
//
//
//    public Drawable getCardImageDrawable() {
//        return this.cardImageDrawable;
//    }
//
//    public void setCardImageDrawable(Drawable cardImageDrawable) {
//        this.cardImageDrawable = cardImageDrawable;
//    }
//
//    public Drawable getCardLikeImageDrawable() {
//        return this.cardLikeImageDrawable;
//    }
//
//    public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
//        this.cardLikeImageDrawable = cardLikeImageDrawable;
//    }
//
//    public Drawable getCardDislikeImageDrawable() {
//        return this.cardDislikeImageDrawable;
//    }
//
//    public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
//        this.cardDislikeImageDrawable = cardDislikeImageDrawable;
//    }
}
