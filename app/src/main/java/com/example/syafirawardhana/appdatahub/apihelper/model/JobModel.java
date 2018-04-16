package com.example.syafirawardhana.appdatahub.apihelper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Syafira W Ardhana on 04/04/2018.
 */

public class JobModel implements Parcelable {
    public String id;
    public String name;
    public String description;
    public String status;

    public JobModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        status = in.readString();
    }

    public static final Creator<JobModel> CREATOR = new Creator<JobModel>() {
        @Override
        public JobModel createFromParcel(Parcel in) {
            return new JobModel(in);
        }

        @Override
        public JobModel[] newArray(int size) {
            return new JobModel[size];
        }
    };

    public JobModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(status);
    }
}
