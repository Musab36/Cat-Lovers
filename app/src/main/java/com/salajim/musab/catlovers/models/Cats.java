package com.salajim.musab.catlovers.models;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Cats implements Parcelable {
    @SerializedName("id") String mId;
    @SerializedName("name") String mName;
    @SerializedName("breeds") String mBreeds;
    @SerializedName("description") String mDescription;
    @SerializedName("url") String imageUrl;
    @SerializedName("origin") String mOrigin;

    public Cats() {}

    public Cats(String mId, String mName, String mBreeds, String mDescription, String imageUrl, String mOrigin) {
        this.mId = mId;
        this.mName = mName;
        this.mBreeds = mBreeds;
        this.mDescription = mDescription;
        this.imageUrl = imageUrl;
        this.mOrigin = mOrigin;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getBreeds() {
        return mBreeds;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOrigin() {
        return mOrigin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mBreeds);
        dest.writeString(this.mDescription);
        dest.writeString(this.imageUrl);
        dest.writeString(this.mOrigin);
    }

    protected Cats(Parcel in) {
        this.mId = in.readString();
        this.mName = in.readString();
        this.mBreeds = in.readString();
        this.mDescription = in.readString();
        this.imageUrl = in.readString();
        this.mOrigin = in.readString();
    }

    public static final Creator<Cats> CREATOR = new Creator<Cats>() {
        @Override
        public Cats createFromParcel(Parcel source) {
            return new Cats(source);
        }

        @Override
        public Cats[] newArray(int size) {
            return new Cats[size];
        }
    };
}