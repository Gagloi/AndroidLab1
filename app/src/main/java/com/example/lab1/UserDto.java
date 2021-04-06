package com.example.lab1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class UserDto implements Parcelable {

    private String name;
    private String lastName;
    private Date date;
    private String gender;

    protected UserDto(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        gender = in.readString();
        date = (Date) in.readSerializable();
    }

    public UserDto(String name, String lastName, Date date, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.gender = gender;
    }

    public static final Creator<UserDto> CREATOR = new Creator<UserDto>() {
        @Override
        public UserDto createFromParcel(Parcel in) {
            return new UserDto(in);
        }

        @Override
        public UserDto[] newArray(int size) {
            return new UserDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(gender);
        dest.writeSerializable(date);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", date=" + date +
                ", gender='" + gender + '\'' +
                '}';
    }
}
