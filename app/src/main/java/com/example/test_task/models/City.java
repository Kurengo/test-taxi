package com.example.test_task.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {
    @SerializedName("city_id")
    private Integer cityId;

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("city_latitude")
    private Double cityLatitude;

    @SerializedName("city_longitude")
    private Double cityLongitude;

    @SerializedName("city_spn_latitude")
    private Double citySpnLatitude;

    @SerializedName("city_spn_longitude")
    private Double citySpnLongitude;

    @SerializedName("last_app_android_version")
    private Integer lastAppAndroidVersion;

    @SerializedName("transfers")
    private boolean transfers;

    @SerializedName("client_email_required")
    private boolean clientEmailRequired;

    @SerializedName("registration_promocode")
    private boolean registrationPromocode;

    public Integer getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public Double getCityLatitude() {
        return cityLatitude;
    }

    public Double getCityLongitude() {
        return cityLongitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cityLatitude=" + cityLatitude +
                ", cityLongitude=" + cityLongitude +
                ", citySpnLatitude=" + citySpnLatitude +
                ", citySpnLongitude=" + citySpnLongitude +
                ", lastAppAndroidVersion=" + lastAppAndroidVersion +
                ", transfers=" + transfers +
                ", clientEmailRequired=" + clientEmailRequired +
                ", registrationPromocode=" + registrationPromocode +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.cityId);
        dest.writeString(this.cityName);
        dest.writeValue(this.cityLatitude);
        dest.writeValue(this.cityLongitude);
        dest.writeValue(this.citySpnLatitude);
        dest.writeValue(this.citySpnLongitude);
        dest.writeValue(this.lastAppAndroidVersion);
        dest.writeByte(this.transfers ? (byte) 1 : (byte) 0);
        dest.writeByte(this.clientEmailRequired ? (byte) 1 : (byte) 0);
        dest.writeByte(this.registrationPromocode ? (byte) 1 : (byte) 0);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.cityId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cityName = in.readString();
        this.cityLatitude = (Double) in.readValue(Double.class.getClassLoader());
        this.cityLongitude = (Double) in.readValue(Double.class.getClassLoader());
        this.citySpnLatitude = (Double) in.readValue(Double.class.getClassLoader());
        this.citySpnLongitude = (Double) in.readValue(Double.class.getClassLoader());
        this.lastAppAndroidVersion = (Integer) in.readValue(Integer.class.getClassLoader());
        this.transfers = in.readByte() != 0;
        this.clientEmailRequired = in.readByte() != 0;
        this.registrationPromocode = in.readByte() != 0;
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}