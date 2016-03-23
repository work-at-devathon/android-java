package com.parse.starter;


import android.os.Parcel;
import android.os.Parcelable;

public class ObjectA implements Parcelable{
    private Object object;

    @Override
    public int describeContents() {
        return 0;
    }
    public ObjectA(Parcel in) {

        readFromParcel(in);
    }
    private void readFromParcel(Parcel in) {

        object=in.readBundle();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(object);
    }
    public void setvalue(Object a)
    {
        this.object=a;

    }
    public Object getvalue()
    {
        return object;
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public ObjectA createFromParcel(Parcel in) {
                    return new ObjectA(in);
                }

                public ObjectA[] newArray(int size) {
                    return new ObjectA[size];
                }
            };

}
