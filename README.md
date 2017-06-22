# ProxerLibJava [![Release](https://jitpack.io/v/proxer/ProxerLibJava.svg)](https://jitpack.io/#proxer/ProxerLibJava) [![CircleCI](https://circleci.com/gh/proxer/ProxerLibJava.svg?style=shield)](https://circleci.com/gh/proxer/ProxerLibJava) [![Coverage](https://codecov.io/gh/proxer/ProxerLibJava/branch/master/graph/badge.svg)](https://codecov.io/gh/proxer/ProxerLibJava)

## What is this?

This is an `Java` and `Android` library, implementing the API of the [Proxer.me](https://proxer.me/) website. This is currently `v1`. Built on [Retrofit](https://github.com/square/retrofit), [OkHttp](https://github.com/square/okhttp) and [Moshi](https://github.com/square/moshi), it offers great performance and flexability.

## Including in your project

The preferred build system is [Gradle](https://gradle.org/).<br>
Add this to your project wide build.gradle:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

And this to your module build.gradle:

```groovy
dependencies {
    compile 'com.github.proxer:ProxerLibJava:3.0.3'
}
```

You can also download the `jar` directly from [here](https://jitpack.io/com/github/proxer/ProxerLibJava/3.0.3/ProxerLibJava-3.0.3.jar), if you prefer.

> Note that this jar does not come with the required dependencies. You have to include those manually in that case.

## Usage

### Initialization

All requests are done through the `ProxerApi` class. You initialize an instance with the `ProxerApi.Builder`.<br>
The most simple initialization looks like this:

```java
ProxerApi api = new ProxerConnection.Builder("yourApiKey").build();
```

You can customize the `ProxerApi` in the following ways:

Method              | Description
------------------- | -----------------------------------------------------------------------------------------------------------------------------------------------------
`loginTokenManager` | Sets an own `LoginTokenManager` for automatic login. This will covered later.
`userAgent`         | Sets a custom Http `User-Agent` to be used. This defaults to `ProxerLibJava/<Version>` otherwise. Pass an empty String if you don't want to sent one.
`moshi`             | Sets a custom `Moshi` instance, used for parsing. Note, that various adapters are still applied, to make the API work properly.
`client`            | Sets a custom `OkHttpClient` instance, used for Http requests. Note, that various interceptors are still applied, to make the API work properly.
`retrofit`          | Sets a custom `Retrofit` instance.
`logginStrategy`    | Allows for simple logging of Http requests, sent through the API. Available strategies are: `NONE`, `API`, `ALL`.

### Sending a request

The API is divided in classes for the various request, similar to the actual REST-API.<br>
A simple query for the latest news looks like this:

```java
List<NewsArticle> result = api.notifications()
                .news()
                .build()
                .execute();
```

The `build` method returns a `ProxerCall` object. If you are familiar with `OkHttp`, this works exactly the same. The `ProxerCall` object also allows for asynchronous requests:

```java
api.notifications().news()
        .page(0)
        .limit(10)
        .build()
        .enqueue(result -> {
            // Show the result.
        }, error -> {
            // Show the error.
        });
```

As you can see in the example above, the individual endpoints also allow for various options.

### Cancelling a request

You might want to cancel a request, especially if using on `Android`:

```java
ProxerCall call = api.[...].build()

call.cancel();
```

> It is an error to execute a cancelled `ProxerCall`.

### Error handling

All errors are encapsulated in a `ProxerException`. It offers the following info about the error:

Method               | Description
-------------------- | -------------------------------------------------------------------------------------
`getErrorType`       | Returns the general error type.
`getServerErrorType` | Returns the type of server error.
`getMessage`         | Returns the associated message of the error if it was a server error. Otherwise null.

These are the available general error types:

Type      | Description
--------- | ----------------------------------------------------------------------------------------------------------------------------------------------------
`SERVER`  | An error on the server occurred. This can for example be `LOGIN_INVALID_CREDENTIALS`, signaling that incorrect credentials were passed during login.
`TIMEOUT` | The server did not respond in time.
`IO`      | The data transfer failed. This can happen if no network connectivity is present for example.
`PARSING` | The server sent broken data. This happens mostly when the REST-API changed and this library was not adjusted yet.
`UNKNOWN` | Any other type of error. This is an internal error in most cases.

There is a wide range of server errors. Consult the [ProxerException](https://github.com/proxer/ProxerLibJava/blob/master/library/src/main/java/me/proxer/library/api/ProxerException.java) class for details.<br>
Here is a selection of important ones:

Type                       | Description
-------------------------- | ------------------------------------------------------
`INSUFFICIENT_PERMISSIONS` | Your API-key is not allowed to access the API.
`IP_BLOCKED`               | You sent to many request in a specific time duration.
`*_INVALID_*`              | The passed data was not correct.
`*_LOGIN_REQUIRED`         | This section can only be accessed as a logged in user.

You catch errors like this (synchronous):

```java
try {
    api.notifications().news()
            .build()
            .execute();
} catch (ProxerException error) {
    // Handle the error here.
}
```

Or like this (asynchronous):

```java
api.notifications().news()
        .build()
        .enqueue(result -> {
            // No error occurred.
        }, error -> {
            // Handler the error here.
        });
```

If you just want to present the error to the user, it can be done like so:

```java
void handleError(ProxerException error) {
    switch (error.getErrorType()) {
        case SERVER:
            print(error.getMessage());

            break;
        case TIMEOUT:
            print("The server did not respond in time.");

            break;
        case IO:
            print("Data transfer failed. Check your network connection.");

            break;
        case PARSING:
            print("The server sent broken data.");

            break;
        case UNKNOWN:
            print("An unkown error occurred.");

            break;
    }
}
```

### Login

The `ProxerApi` offers a mechanism for automatic login.<br>
If you call the `user.login` API, the relevant information is stored automatically. If you then call `user.logout`, is is also removed automatically.

You can customize this behaviour through a custom `LoginTokenManager`.<br>
The most simple one looks like this (Such a `LoginTokenManager` is used if you do not pass one):

```java
ProxerApi api = new ProxerApi.Builder("yourApiKey")
        .loginTokenManager(new LoginTokenManager() {

            private String token;

            @Nullable
            @Override
            public String provide() {
                return token;
            }

            @Override
            public void persist(@Nullable String loginToken) {
                token = loginToken;
            }
        })
        .build();
```

> The token is only stored in memory with the default `LoginTokenManager`. This means, that you lose the login information when the application terminates. You may want to persist it into a `File` or `SharedPreferences` on `Android`.

### Utils

This library offers two utility classes: `ProxerUrls` and `ProxerUtils`.

#### ProxerUrls

The `ProxerUrls` class has various static methods for getting often needed urls.<br>
These are returned as an [HttpUrl](https://medium.com/square-corner-blog/okhttps-new-url-class-515460eea661), which has various advantages above the default `Java` classes.

Retrieving the url to the image of an user can be done like so:

```java
HttpUrl url = ProxerUrls.userImage("image property of the UserInfo entity here");
```

#### ProxerUtils

The API often returns entities, which have enums as properties. You may want to get the `String` representation, which is actually used for communication. To do so, you can use the `getApiEnumName` method:

```java
String genreAsString = ProxerUtils.getApiEnumName(Genre.ACTION);
```

The other way around is also available:

```java
Genre genreAsEnum = ProxerUtils.toApiEnum(Genre.class, "Action");
```

### ProGuard

If you are using ProGuard, the following config is required:

```proguard
# Config for ProxerLibJava itself
-keep enum me.proxer.library.** {
    **[] $VALUES;
    public *;
}

# Config for OkHttp and Okio
-dontwarn okio.**
-dontwarn javax.annotation.**

# Config for Moshi
-keepclassmembers class ** {
    @com.squareup.moshi.FromJson *;
    @com.squareup.moshi.ToJson *;
}

# Config for Retrofit
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.http.* <methods>;
}
```

### More

You can find detailed JavaDoc [here](https://jitpack.io/com/github/proxer/ProxerLibJava/3.0.3/javadoc/).

## Working on the library

Recommended development environment is [IntelliJ IDEA](https://www.jetbrains.com/idea/).<br>
As this project uses [Lombok](https://projectlombok.org/), you will also need the plugin. Remember to turn on annotation processing to make compilation work correctly.

## Dependencies

This library highly relies on [Retrofit](https://github.com/square/retrofit) and [OkHttp](http://square.github.io/okhttp/) by [Square](https://github.com/square) for the network communication.<br>
Moreover it uses [Moshi](https://github.com/square/moshi) for response parsing and the [Jetbrains Annotations](https://www.jetbrains.com/help/idea/2017.1/nullable-and-notnull-annotations.html) to improve code style and provide IDE support.

## Contributions and contributors

A guide for contribution can be found [here](.github/CONTRIBUTING.md).

- [@Desnoo](https://github.com/desnoo) for implementing several APIs.
