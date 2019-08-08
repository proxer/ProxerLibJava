@file:JvmName("News")

package me.proxer.sample

import me.proxer.library.ProxerApi
import me.proxer.library.ProxerException

fun main() {
    val api = ProxerApi.Builder(readApiKey()).build()

    try {
        val news = api.notifications.news()
            .build()
            .safeExecute()
            .joinToString("\n") { (_, _, _, _, subject, _, _, _, author) -> "$subject written by $author" }

        println()
        println("These are the latest news:\n$news")
    } catch (exception: ProxerException) {
        println("Something went wrong: " + exception.message)
    }
}

private fun readApiKey(): String {
    print("Please pass your api key: ")

    return readLine() ?: throw IllegalStateException("No input detected.")
}
