package me.proxer.library.api.user

import retrofit2.Retrofit

/**
 * API for the User class.
 *
 * @author Ruben Gees
 */
class UserApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun login(username: String, password: String): LoginEndpoint {
        return LoginEndpoint(internalApi, username, password)
    }

    /**
     * Returns the respective endpoint.
     */
    fun logout(): LogoutEndpoint {
        return LogoutEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun topTen(userId: String?, username: String?): UserTopTenEndpoint {
        return UserTopTenEndpoint(internalApi, userId, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun info(userId: String?, username: String?): UserInfoEndpoint {
        return UserInfoEndpoint(internalApi, userId, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun about(userId: String?, username: String?): UserAboutEndpoint {
        return UserAboutEndpoint(internalApi, userId, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun mediaList(userId: String?, username: String?): UserMediaListEndpoint {
        return UserMediaListEndpoint(internalApi, userId, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun comments(userId: String?, username: String?): UserCommentsEndpoint {
        return UserCommentsEndpoint(internalApi, userId, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun history(userId: String?, username: String?): UserHistoryEndpoint {
        return UserHistoryEndpoint(internalApi, userId, username)
    }
}
