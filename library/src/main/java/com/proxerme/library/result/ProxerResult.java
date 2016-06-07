package com.proxerme.library.result;

import android.support.annotation.NonNull;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public interface ProxerResult<T> {

    @NonNull
    T getItem();
}
