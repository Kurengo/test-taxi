package com.example.test_task.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cities implements Parcelable {
    @SerializedName("cities")
    private List<City> citiesList;

    public List<City> getCitiesList() {
        return citiesList;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "citiesList=" + citiesList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.citiesList);
    }

    public Cities() {
    }

    protected Cities(Parcel in) {
        this.citiesList = in.createTypedArrayList(City.CREATOR);
    }

    public static final Parcelable.Creator<Cities> CREATOR = new Parcelable.Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel source) {
            return new Cities(source);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };
}