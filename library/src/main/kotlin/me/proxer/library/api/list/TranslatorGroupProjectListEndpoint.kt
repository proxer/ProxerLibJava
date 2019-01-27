package me.proxer.library.api.list

import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.list.TranslatorGroupProject
import me.proxer.library.enums.ProjectState
import me.proxer.library.util.toIntOrNull

/**
 * Endpoint for retrieving a list of projects of a translator group.
 *
 * @author Ruben Gees
 */
class TranslatorGroupProjectListEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : PagingLimitEndpoint<List<TranslatorGroupProject>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var projectState: ProjectState? = null
    private var includeHentai: Boolean? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the state of projects to filter by.
     */
    fun projectState(projectState: ProjectState?) = this.apply { this.projectState = projectState }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    override fun build(): ProxerCall<List<TranslatorGroupProject>> {
        return internalApi.translatorGroupProjectList(
            id, projectState, includeHentai.toIntOrNull(), page, limit
        )
    }
}
