package com.example.syafirawardhana.appdatahub.apihelper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Syafira W Ardhana on 29/03/2018.
 */

public class User implements Parcelable {
    private String id;
    private String username;
    private String email;
    private String organizationId;
    private String roleName;
    private String getOrganizationName;
    private String displayName;
    private String sessionid;


    protected User(Parcel in) {
        id = in.readString();
        username = in.readString();
        email = in.readString();
        organizationId = in.readString();
        roleName = in.readString();
        getOrganizationName = in.readString();
        displayName = in.readString();
        sessionid = in.readString();

    }
    public User(){}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(organizationId);
        dest.writeString(roleName);
        dest.writeString(getOrganizationName);
        dest.writeString(displayName);
        dest.writeString(sessionid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getSessionid() {return sessionid;}

    public void setSessionid(String sessionid) {this.sessionid = sessionid;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGetOrganizationName() {
        return getOrganizationName;
    }

    public void setGetOrganizationName(String getOrganizationName) {
        this.getOrganizationName = getOrganizationName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
