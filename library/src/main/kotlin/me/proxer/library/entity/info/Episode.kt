package me.proxer.library.entity.info

import me.proxer.library.enums.MediaLanguage

/**
 * Base entity holding the common data of an [AnimeEpisode] and [MangaEpisode].
 *
 * @property number The number of this episode.
 * @property language The language.
 *
 * @author Ruben Gees
 */
open class Episode protected constructor(
    open val number: Int,
    open val language: MediaLanguage
)
