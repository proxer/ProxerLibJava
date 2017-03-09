package com.proxerme.library.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * An interface for all entities with an image.
 *
 * @author Ruben Gees
 */
public interface ImageItem {

    /**
     * Returns the image id.
     */
    @NotNull
    String getImageId();
}
