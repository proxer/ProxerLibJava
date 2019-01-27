package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.list.Tag
import me.proxer.library.enums.TagSortCriteria
import me.proxer.library.enums.TagSubType
import me.proxer.library.enums.TagType

/**
 * Endpoint for retrieving the available tags.
 *
 * @author Ruben Gees
 */
class TagListEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<List<Tag>> {

    private var name: String? = null
    private var type: TagType? = null
    private var sortCriteria: TagSortCriteria? = null
    private var subType: TagSubType? = null
    private var sortType: TagSortType? = null

    /**
     * Sets the name to search for.
     */
    fun name(name: String?) = this.apply { this.name = name }

    /**
     * Sets the type to filter by.
     */
    fun type(type: TagType?) = this.apply { this.type = type }

    /**
     * Sets the criteria to sort by.
     */
    fun sortCriteria(sortCriteria: TagSortCriteria?) = this.apply { this.sortCriteria = sortCriteria }

    /**
     * Sets the sub type to filter by.
     */
    fun subType(subType: TagSubType?) = this.apply { this.subType = subType }

    /**
     * Sets the sort type to be ascending.
     */
    fun sortAscending() = this.apply { sortType = TagSortType.ASCENDING }

    /**
     * Sets the sort type to be ascending.
     */
    fun sortDescending() = this.apply { sortType = TagSortType.DESCENDING }

    override fun build(): ProxerCall<List<Tag>> {
        return internalApi.tagList(name, type, sortCriteria, sortType, subType)
    }
}
