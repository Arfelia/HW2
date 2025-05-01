package com.example.hw2;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Parameters implements Parcelable {
    public String insertedNumbers;
    public String lastOperation;
    public Double interim;


    public Parameters(){
        insertedNumbers = null;
        lastOperation = null;
        interim = null;
    }

    protected Parameters(Parcel in) {
        insertedNumbers = in.readString();
        lastOperation = in.readString();
        interim = in.readDouble();
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(insertedNumbers);
        dest.writeString(lastOperation);
        dest.writeDouble(interim);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parameters> CREATOR = new Creator<Parameters>() {
        @Override
        public Parameters createFromParcel(Parcel in) {
            return new Parameters(in);
        }

        @Override
        public Parameters[] newArray(int size) {
            return new Parameters[size];
        }
    };
}