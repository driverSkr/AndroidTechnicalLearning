package com.example.advancedskill.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/*实现了Parcelable接口，这样就必须重写describeContents()和writeToParcel()这两个方法*/
public class User implements Parcelable {

    private String name;
    private int age;

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    //还必须在User类中提供一个名为CREATOR的匿名类实现。这里创建了Parcelable.Creator接口的一个实现，并将泛型指定为User
    //接着需要重写createFromParcel()和newArray()这两个方法
    public static final Creator<User> CREATOR = new Creator<User>() {
        //要创建一个Person对象进行返回
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //describeContents()方法直接返回0就可以了
    @Override
    public int describeContents() {
        return 0;
    }

    //需要调用Parcel的writeXxx()方法，将Person类中的字段一一写出
    //注意，字符串型数据就调用writeString()方法，整型数据就调用writeInt()方法，以此类推
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
