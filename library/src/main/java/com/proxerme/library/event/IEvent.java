package com.proxerme.library.event;

import android.support.annotation.NonNull;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public interface IEvent<T> {

    @NonNull
    T getItem();
}
