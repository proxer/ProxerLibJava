# ProxerLibAndroid [![Release](https://jitpack.io/v/proxer/ProxerLibAndroid.svg)](https://jitpack.io/#proxer/ProxerLibAndroid) [![Release](https://circleci.com/gh/proxer/ProxerLibAndroid.svg?style=shield)](https://circleci.com/gh/proxer/ProxerLibAndroid) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/b4f6582ef2fd4edba232387fcd17b3f2)](https://www.codacy.com/app/geesruben/ProxerLibAndroid?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=proxer/ProxerLibAndroid&amp;utm_campaign=Badge_Grade)

### What is this?

This is an Android library, implementing the API of the
[Proxer.me](https://proxer.me/) website. This is currently `v1`.

### Table of contents

- [Including in your project](#including-in-your-project)
- [Usage](#usage)   
  - [Initialization](#initialization)   
  - [Doing a request](#doing-a-request)   
  - [Cancelling a request](#cancelling-a-request)   
  - [Error handling](#error-handling)   
  - [Images and non-API pages](#images-and-non-api-pages)   
  - [Configuration](#configuration)   
  - [More](#more)   
- [Architecture](#architecture)   
  - [Organisation](#organisation)
  - [Experimental APIs](#experimental-apis)   
- [Extensions](#extensions)   
- [Dependencies](#dependencies)

### Including in your project

Add this to your project build.gradle:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

And this to your module build.gradle:

```groovy
dependencies {
    compile('com.github.proxer:ProxerLibAndroid:1.9.5') {
        transitive = true
    }
}
```

### Usage

##### Initialization

All requests are done through the `ProxerConnection` class. You have to
initialize it with the `ProxerConnection.Builder` before using.  

The construction looks like this:

```java
ProxerConnection proxerConnection = new ProxerConnection.Builder("yourApiKey", this).build();
```

You can customize the connection which will be covered later.

The recommended usage is to use it as a Singleton in an `Application` subclass.  
To do so, add an entry for your subclass in the `AndroidManifest`:

```xml
<application
        android:name=".MainApplication"
        ...
```

Create the class and initialize the `ProxerConnection` in it:

```java
public class MainApplication extends Application {

    public static ProxerConnection proxerConnection;

    @Override
    public void onCreate() {
        super.onCreate();

        proxerConnection = new ProxerConnection.Builder("yourApiKey", this)
                .build();
    }
}
```

This allows you to access the `ProxerConnection` in every part of your
app like this:

```java
ProxerConnection connection = MainApplication.proxerConnection;
```

##### Doing a request

To do a Request, you have to construct one of the available `ProxerRequest`
subclasses and pass it to the `ProxerConnection`.

A simple query for the latest News looks like this:

```java
proxerConnection.execute(new NewsRequest(0), // 0 is the first page
        new ProxerCallback<News[]>() {
            @Override
            public void onSuccess(News[] result) {

            }
        }, new ProxerErrorCallback() {
            @Override
            public void onError(ProxerException exception) {

            }
        });
```

As you can see, you have to pass a `ProxerRequest` subclass, a `ProxerCallback`
and a `ProxerErrorCallback`. The ProxerCallback always returns the specific
result entity (Or `null` for `POST` requests).

Many requests are configurable through either their constructor if the
parameters are obligatory or through `withParameter` builder methods if the
parameters are not obligatory.

The `NewsRequest` has an optional `limit` parameter to limit the amount of items
returned.  
Setting it looks like so:

```java
NewsRequest request = new NewsRequest(0).withLimit(30);
```

As you have seen, you use the `execute` method for asynchronous requests with
callbacks. If you want to execute the request synchronous, you can use the
`executeSynchronized` method. This is useful if you want to manage the threading
yourself or if you are on a background thread already (e.g. `AsyncTask`,
`IntentService`).

A synchronous query for the latest News looks like this:

```java
try {
    News[] latestNews = proxerConnection
            .executeSynchronized(new NewsRequest(0));

    // Do something with the data
} catch (ProxerException exception) {
    // Handle the error
}
```

One thing to note is that all results from `execute` are delivered on the main
thread for you so you can directly update the UI (Note: You still have to check
if your `View`s are available at that time).

##### Cancelling a request

You might want to cancel a request, especially if using in the Android
lifecycle:

```java
ProxerCall call = proxerConnection.execute(...);

call.cancel();
```

The `execute` method returns a `ProxerCall` object, which you can invoke
`cancel` on. Moreover it has methods for retrieving the current status of the
request (`isCancelled` and `isExecuted`).

It is important to always cancel your requests if directly used in an `Activity`
or `Fragment`.  
A simple example for an Activity, retrieving the latest News:

```java
public class NewsActivity extends AppCompatActivity {

    private ProxerCall call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        call = MainApplication.proxerConnection.execute(new NewsRequest(0),
                new ProxerCallback<News[]>() {
                    @Override
                    public void onSuccess(News[] result) {
                        // Update the UI
                    }
                }, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        // Show error
                    }
                });
    }

    @Override
    protected void onDestroy() {
        call.cancel();

        super.onDestroy();
    }
}
```

##### Error handling

If a request fails, a ProxerException is thrown or passed to your callback.
This Exception contains useful information on what went wrong.

Handling the Exception might look like this:

```java
private void handleException(ProxerException exception) {
    switch (exception.getErrorCode()) {
        case ProxerException.NETWORK:
            Toast.makeText(getContext(), "Network is misconfigured or not available.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.PROXER:
            handleProxerException(exception);

            break;
        case ProxerException.UNPARSABLE:
            Toast.makeText(getContext(), "The server sent broken data.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.CANCELLED:
            Toast.makeText(getContext(), "The request was cancelled.",
                    Toast.LENGTH_SHORT).show();

            // This might not be needed in all cases, see "Configuration"

            break;
    }
}
```

If a server error occured, you can obtain more granular information like so:

```java
private void handleProxerException(ProxerException exception) {
    switch (exception.getProxerErrorCode()) {
        case ProxerException.INFO_ENTRY_ALREADY_IN_LIST:
            Toast.makeText(getContext(), "The entry is already in the list.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.INSUFFICIENT_RIGHTS:
            Toast.makeText(getContext(), "You do not have the rights for this page",
                    Toast.LENGTH_SHORT).show();

            break;
        ...
    }
}
```

This example is not complete as there are many more possible errors which you
can find in the `ProxerException` class.

In most cases it is enough (and much more easy) to show the error message to the
user:

```java
if (exception.getErrorCode() == ProxerException.PROXER) {
    Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
}
```

##### Images and non-API pages

You might want to load images for various entities or open a page which is not
part of the API.

For this purpose there is a `ProxerUrlHolder` class, which provides methods to
obtain various often needed Urls.  
One example is the Url of the image of a News:

```java
private String getUrlToNewsImage(News news) {
    return ProxerUrlHolder.getNewsImageUrl(news.getId(), news.getImageId())
            .toString();
}
```

##### Configuration

The `ProxerConnection` allows for customization. The internally used libs are
pluggable and you can specify some other arguments.  
Here is an example with all available customizations:

```java
CookieJar cookieJar = ...;
OkHttpClient httpClient = ...;
Moshi moshi = ...;

ProxerConnection proxerConnection = new ProxerConnection.Builder("yourApiKey", cookieJar)
        .withCustomOkHttp(httpClient)
        .withCustomMoshi(moshi)
        .withDeliverCancelledRequests(true)
        .build();
```

The first line uses the alternate constructor, in which you pass a custom
`CookieJar` instead of a `Context`. The `Context` is only used to construct a
persistent `CookieJar` internally, based on `SharedPreferences`.

The second line passes a custom `OkHttpClient`. Note that it has no effect to
set your `CookieJar` to it as that will be overriden.

The third line passes a custom `Moshi` instance.

The last line enables cancelled request delivery. This is disabled by default,
meaning that your callbacks will not be called if an request was cancelled.

##### More

You can find detailed JavaDoc
[here](https://jitpack.io/com/github/proxer/ProxerLibAndroid/1.9.5/javadoc/).

### Architecture

##### Organisation

The actual REST API is organized in classes. This library tries to mirror those
as closely as possible. For each class there is a package with all requests in
the `connection` package. The `NewsRequest` for example is in the `notification`
package just as in the REST API.

[This page](https://proxer.me/wiki/Proxer_API/v1) contains more information
about the REST API.

##### Experimental APIs

In some versions there might be an `experimental` package. This package contains
requests or other features which *should* work, but are not documented and
officially supported. Note that those are potentially dangerous as they might
get removed without further notice.

### Extensions

You can easily write your own requests if this library does not provide a
request yet. To do so, have a look at the `entity`, `result` and `request`
packages for each API class. You have to define a `Entity`, a `ProxerResult`
subclass and a `ProxerRequest` subclass, each with an easy to implement
interface.  
Don't forget to do a Pull Request!

### Dependencies

This library highly relies on [OkHttp](http://square.github.io/okhttp/) by
[Square](https://github.com/square) for the network communication.  
Moreover it uses
[Moshi](https://github.com/square/moshi) for response parsing,
[PersistentCookieJar](https://github.com/franmontiel/PersistentCookieJar) for
Cookie management and
[Android Support Annotations](http://tools.android.com/tech-docs/support-annotations)
to improve the code style and provide IDE support.
