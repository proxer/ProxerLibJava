package com.proxerme.library.connection.info.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.info.entity.Synonym;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Class that represents the results of the {@link com.proxerme.library.connection.info.request.SynonymRequest}.
 *
 * @author Desnoo
 */
public class SynonymResult implements ProxerResult<Synonym[]> {

    @Body(name = "data")
    Synonym[] item;

    /**
     * Private constructor.
     */
    SynonymResult() {
    }

    /**
     * The copy constructor.
     *
     * @param item An array of synonyms to assign.
     */
    public SynonymResult(@NonNull Synonym[] item) {
        this.item = item;
    }

    /**
     * Gets the Array of synonyms.
     *
     * @return An array of synonym objects.
     */
    @Override
    public Synonym[] getItem() {
        return item;
    }
}
