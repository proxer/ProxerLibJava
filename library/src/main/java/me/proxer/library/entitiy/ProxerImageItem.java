package me.proxer.library.entitiy;

/**
 * An interface for all entities with an image.
 *
 * @author Ruben Gees
 */
public interface ProxerImageItem {

    /**
     * Returns the image.
     * <p>
     * This can either be an id or a direct link, depending on the API.
     */
    String getImage();
}
