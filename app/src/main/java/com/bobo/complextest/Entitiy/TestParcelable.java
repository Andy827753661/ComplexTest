
package com.bobo.complextest.Entitiy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/30. File -> Settings -> Pugins -> Browse
 * Repositories 如下，输入android parcelable code generator。 使用：
 * 按下Alt+Insert，选择Palcelable，选择需要的属性，按下OK
 */

public class TestParcelable implements Parcelable {

    private int age;

    private String name;

    private String gender;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.age);
        dest.writeString(this.name);
        dest.writeString(this.gender);
    }

    public TestParcelable() {
    }

    protected TestParcelable(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        this.gender = in.readString();
    }

    public static final Parcelable.Creator<TestParcelable> CREATOR = new Parcelable.Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel source) {
            return new TestParcelable(source);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };
}
