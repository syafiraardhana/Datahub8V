package com.example.syafirawardhana.appdatahub.apihelper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Syafira W Ardhana on 04/04/2018.
 */

public class SurveyModel implements Parcelable {
    public String id,name,description,status;

    public SurveyModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        status = in.readString();
    }

    public static final Creator<SurveyModel> CREATOR = new Creator<SurveyModel>() {
        @Override
        public SurveyModel createFromParcel(Parcel in) {
            return new SurveyModel(in);
        }

        @Override
        public SurveyModel[] newArray(int size) {
            return new SurveyModel[size];
        }
    };

    public SurveyModel() {}

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
