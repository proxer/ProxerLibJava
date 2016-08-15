package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * This class is general class to match a simple request with an id.
 *
 * Use the this class with {@link com.proxerme.library.connection.messenger.request.SetFavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnfavourRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetBlockRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnblockRequest},
 * {@link com.proxerme.library.connection.messenger.request.SetUnreadRequest} or
 * {@link com.proxerme.library.connection.messenger.request.SetReportRequest}.
 *
 *
 * @author Desnoo
 */
public class SetAction implements Parcelable {

    public static final Creator<SetAction> CREATOR = new Creator<SetAction>() {
        @Override
        public SetAction createFromParcel(Parcel in) {
            return new SetAction(in);
        }

        @Override
        public SetAction[] newArray(int size) {
            return new SetAction[size];
        }
    };

    String status;

    /**
     * The Constructor.
     *
     * @param status The status of the response of the request.
     */
    public SetAction(@NonNull String status) {
        this.status = status;
    }

    /**
     * The Constructor to parse the parcel.
     *
     * @param in The parcel to parse.
     */
    protected SetAction(Parcel in) {
        status = in.readString();
    }


    /**
     * Get the status of the {@link com.proxerme.library.connection.messenger.request.SetFavourRequest}.
     *
     * @return A string containing the status of a set request.
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

        SetAction setAction = (SetAction) o;

        return status.equals(setAction.status);

    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
