package me.proxer.library.entity

import java.util.Date

/**
 * An interface for all entities with a time.
 *
 * @author Ruben Gees
 */
interface ProxerDateItem {

    /**
     * The time.
     */
    val date: Date
}
