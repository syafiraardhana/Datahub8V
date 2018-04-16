package com.example.syafirawardhana.appdatahub.apihelper.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by Syafira W Ardhana on 09/04/2018.
 */
public class DetailModel implements Parcelable {
    public String id;
    public String uuid;
    public String surveyId;
    public String surveyorId;
    public String userId;
    public String jobId;
    public String timezone;
    public String status;
    public String createdDate;
    public String createdAt;
    public String updatedAt;
    public Map<String, Question> questions;
    public Map<String, Answer> answers;
    public String answer;

    public DetailModel(){}

    protected DetailModel(Parcel in) {
        id = in.readString();
        uuid = in.readString();
        surveyId = in.readString();
        surveyorId = in.readString();
        userId = in.readString();
        jobId = in.readString();
        timezone = in.readString();
        status = in.readString();
        createdDate = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
//        questions = in.createStringArrayList();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(uuid);
        dest.writeString(surveyId);
        dest.writeString(surveyorId);
        dest.writeString(userId);
        dest.writeString(jobId);
        dest.writeString(timezone);
        dest.writeString(status);
        dest.writeString(createdDate);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
//        dest.writeStringList(questions);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailModel> CREATOR = new Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel in) {
            return new DetailModel(in);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };

    public String questionsAnwersText() {
        if (questions == null || questions.isEmpty()) return "-";
        if (answers == null || answers.isEmpty()) return "-";
        String text = "";

        for (Map.Entry<String, Question> entry : questions.entrySet()) {
            String key = entry.getKey();
            if (!answers.containsKey(key)) {
                continue;
            }
            Answer answer = answers.get(key);
            Question question = questions.get(key);
            text += question.getCaption() + "? " + answer.getType() + "\n";
        }

        return text;
    }
}
