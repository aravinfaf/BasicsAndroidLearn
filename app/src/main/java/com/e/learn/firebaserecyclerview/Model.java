package com.e.learn.firebaserecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

    public String mId, mTitle, mDesc;

    public Model() {}

    public Model(String mId, String mTitle, String mDesc) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mDesc = mDesc;
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

       parcel.writeString(this.mId);
       parcel.writeString(this.mTitle);
       parcel.writeString(this.mDesc);
    }

    Model(Parcel parcel){
        this.mId=parcel.readString();
        this.mDesc=parcel.readString();
        this.mTitle=parcel.readString();
    }
}
