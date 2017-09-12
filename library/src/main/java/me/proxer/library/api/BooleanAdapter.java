package me.proxer.library.api;

import com.squareup.moshi.FromJson;

/**
 * @author Ruben Gees
 */
class BooleanAdapter {

    private PrimitiveBooleanAdapter delegate = new PrimitiveBooleanAdapter();

    @FromJson
    Boolean fromJson(final Object json) {
        return delegate.fromJson(json);
    }
}
