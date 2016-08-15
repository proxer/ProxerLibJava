package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * The respone class used by {@link com.proxerme.library.connection.messenger.request.SetFavourRequest}
 * and GetFavour
 *
 * @author Desnoo
 */
public class Favour implements Parcelable {

    public static final Creator<Favour> CREATOR = new Creator<Favour>() {
        @Override
        public Favour createFromParcel(Parcel in) {
            return new Favour(in);
        }

        @Override
        public Favour[] newArray(int size) {
            return new Favour[size];
        }
    };

    String status;

    /**
     * The Constructor.
     *
     * @param status The status of the response of the request.
     */
    public Favour(@NonNull String status) {
        this.status = status;
    }

    /**
     * The Constructor to parse the parcel.
     *
     * @param in The parcel to parse.
     */
    protected Favour(Parcel in) {
        status = in.readString();
    }


    /**
     * Get the status of the {@link com.proxerme.library.connection.messenger.request.SetFavourRequest}.
     *
     * @return A string containing the status of the favor request.
     */
    @NonNull
    public String getStatus() {
        return status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favour favour = (Favour) o;

        return status.equals(favour.status);

    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
