package com.console.gbversion.whatsscan.Application.Model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class StatusModel implements Parcelable {
    public static final Creator<StatusModel> CREATOR = new Creator<StatusModel>() { // from class: com.console.gbversion.whatsscan.Application.Model.StatusModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusModel createFromParcel(Parcel parcel) {
            return new StatusModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusModel[] newArray(int i) {
            return new StatusModel[i];
        }
    };
    private String filepath;
    public boolean selected = false;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public StatusModel(String str) {
        this.filepath = str;
    }

    protected StatusModel(Parcel parcel) {
        this.filepath = parcel.readString();
    }

    public String getFilePath() {
        return this.filepath;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.filepath);
    }
}
