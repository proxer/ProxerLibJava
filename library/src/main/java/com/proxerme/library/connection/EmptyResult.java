package com.proxerme.library.connection;

import android.support.annotation.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class EmptyResult extends ProxerResult<Void> {

    protected EmptyResult() {
    }

    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
