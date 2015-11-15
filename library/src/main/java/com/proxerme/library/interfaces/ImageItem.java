package com.proxerme.library.interfaces;

import android.support.annotation.NonNull;

/**
 * An interface for all entities with an iamge.
 *
 * @author Ruben Gees
 */
public interface ImageItem {

    /**
     * Returns the image of the inheriting entity.
     *
     * @return The image.
     */
    @NonNull
    String getImageId();

}
