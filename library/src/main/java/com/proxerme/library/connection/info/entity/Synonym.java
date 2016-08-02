package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

/**
 * An entity holding the synonyms of an entry name (Anime, Manga).
 *
 * @author Desnoo
 */
public class Synonym implements Parcelable {

    public static final Creator<Synonym> CREATOR = new Creator<Synonym>() {
        @Override
        public Synonym createFromParcel(Parcel in) {
            return new Synonym(in);
        }

        @Override
        public Synonym[] newArray(int size) {
            return new Synonym[size];
        }
    };

    @Body(name = "id")
    String id;
    @Body(name = "eid")
    String entryId;
    @Body(name = "type")
    String type;
    @Body(name = "name")
    String name;

    /**
     * Private Constructor.
     */
    Synonym() {
    }

    /**
     * The Constructor.
     *
     * @param id      The id.
     * @param entryId The id of the entry.
     * @param type    The type of the Synonym.
     * @param name    The name.
     */
    public Synonym(@NonNull String id, @NonNull String entryId, @NonNull String type, @NonNull String name) {
        this.id = id;
        this.entryId = entryId;
        this.type = type;
        this.name = name;
    }

    /**
     * Constructor to parse parcel.
     *
     * @param in The parcel to parse.
     */
    protected Synonym(Parcel in) {
        id = in.readString();
        entryId = in.readString();
        type = in.readString();
        name = in.readString();
    }


    /**
     * Returns the id of this name.
     *
     * @return The id.
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the corresponding entry(Anime/Manga id) of this entry.
     *
     * @return The id of the corresponding entry.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the type of this entry.
     *
     * @return The type.
     */
    @NonNull
    public String getType() {
        return type;
    }

    /**
     * Returns the name of this entry.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(entryId);
        parcel.writeString(type);
        parcel.writeString(name);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Synonym synonym = (Synonym) o;

        if (!id.equals(synonym.id)) return false;
        if (!entryId.equals(synonym.entryId)) return false;
        if (!type.equals(synonym.type)) return false;
        return name.equals(synonym.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
