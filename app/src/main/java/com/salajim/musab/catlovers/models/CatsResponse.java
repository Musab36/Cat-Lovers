package com.salajim.musab.catlovers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatsResponse implements Parcelable {
    @SerializedName("url")

    private List<Cats> url;

    public List<Cats> getImages() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.url);
    }

    public CatsResponse() {}

    protected CatsResponse(Parcel in) {
        this.url = in.createTypedArrayList(Cats.CREATOR);
    }

    public static final Creator<CatsResponse> CREATOR = new Creator<CatsResponse>() {
        @Override
        public CatsResponse createFromParcel(Parcel source) {
            return new CatsResponse(source);
        }

        @Override
        public CatsResponse[] newArray(int size) {
            return new CatsResponse[size];
        }
    };
}
