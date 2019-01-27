package me.proxer.library.entity

/**
 * An interface for all entities with an image.
 *
 * @author Ruben Gees
 */
interface ProxerImageItem {

    /**
     * The image.
     * This can either be an id or a direct link, depending on the API.
     */
    val image: String
}
