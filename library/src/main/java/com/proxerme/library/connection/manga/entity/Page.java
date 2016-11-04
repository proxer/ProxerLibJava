package com.proxerme.library.connection.manga.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * Entity representing a single page from a {@link Chapter}.
 *
 * @author Ruben Gees
 */
public class Page implements Parcelable {

    public static final Parcelable.Creator<Page> CREATOR = new Parcelable.Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel source) {
            return new Page(source);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    private String name;
    private int height;
    private int width;

    /**
     * The constructor.
     *
     * @param name   The name of the file.
     * @param height The height of the image.
     * @param width  The width of the image.
     */
    public Page(@NonNull String name, @IntRange(from = 0) int height,
                @IntRange(from = 0) int width) {
        this.name = name;
        this.height = height;
        this.width = width;
    }

    protected Page(Parcel in) {
        this.name = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
    }

    /**
     * Returns the filename.
     *
     * @return The filename.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the height.
     *
     * @return The height.
     */
    @IntRange(from = 0)
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width.
     *
     * @return The width.
     */
    @IntRange(from = 0)
    public int getWidth() {
        return width;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (height != page.height) return false;
        if (width != page.width) return false;
        return name.equals(page.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + height;
        result = 31 * result + width;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
    }
}
