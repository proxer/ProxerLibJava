# ProxerLibAndroid [![Release](https://jitpack.io/v/proxer/ProxerLibAndroid.svg)](https://jitpack.io/#proxer/ProxerLibAndroid)

### What is this?

This is a library, prviding some core functionality for an Android App aiming
to implement the API of the [Proxer.me](https://proxer.me/) website.

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
    compile('com.github.proxer:ProxerLibAndroid:1.9.0@aar') {
        transitive = true
    }
}
```

### Usage

##### Initialization

Before using the API, you have to set your API key. This is required for all
requests and an Exception is thrown if no key is set.

It is recommended that you set your key in the `onCreate` method of your
`Application` like this:

```java
@Override
public void onCreate() {
    super.onCreate();

    ProxerConnection.init("Your API key");
}
```

##### Making a request

You can make requests through one of the `ProxerRequest` subclasses. Read the
![Architecture](#architecture) paragraph for info on how those are organized.

A query for the latest News looks like this:

```java
try {
    NewsResult result = new NewsRequest(0).executeSynchronized();  
    News[] news = result.getItem();

    //Do something with the result
} catch (ProxerException exception) {
    //Handle the error
}
```

As you can see, the code above uses the `executeSynchronized` method. Network
requests have to be asynchronous on Android to avoid the UI to freeze. For that
purpose each request comes with the `execute` method, which is asynchronous.

The same query with the `execute` method looks like this:

```java
new NewsRequest(0).execute(new ProxerRequest.ProxerCallback<NewsResult>() {
    @Override
    public void onSuccess(NewsResult result) {
        News[] news = result.getItem();

        //Do something with the result
    }
}, new ProxerRequest.ProxerErrorCallback() {
    @Override
    public void onError(ProxerErrorResult result) {
        ProxerException exception = result.getItem();

        //Handle the error
    }
});
```

As you can see, you have to pass two callbacks to the method. One for a
successful request and one in case of an error. There is always a request
specific `ProxerResult` subclass (`NewsResult` in this case) and a
`ProxerErrorResult` with the `getItem` method to retrieve the data.

One thing to note is that all results are delivered on the main thread for you
so you can directly update the UI (Note: You still have to check if your Views
are available at that time).

##### Cancelling a request

You might want to cancel requests if the user exits the screen or cancels the
action. To do that, you can use the `cancel` method of the `ProxerConnection`.

Cancelling all News requests looks like this:

```java
ProxerConnection.cancel(ProxerTag.NEWS);
```

`ProxerTag` contains tags for all requests.
Note: You won't get a result in your request callbacks if you cancel it.

##### Error handling

When a request fails, a ProxerException is returned or passed to your callback.
This Exception contains useful information on what went wrong.

Handling the Exception might look like this:

```java
private void handleException(ProxerException exception) {
    switch (exception.getErrorCode()) {
        case ProxerException.IO:
            Toast.makeText(getContext(), "A read/write operation failed.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.NETWORK:
            Toast.makeText(getContext(), "Network is misconfigured or not available.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.PROXER:
            handleProxerException(exception);

            break;
        case ProxerException.TIMEOUT:
            Toast.makeText(getContext(), "The server didn't answer in time.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.UNKNOWN:
            Toast.makeText(getContext(), "An unknown error occured.",
                    Toast.LENGTH_SHORT).show();

            break;
        case ProxerException.UNPARSEABLE:
            Toast.makeText(getContext(), "The server sent broken data.",
                    Toast.LENGTH_SHORT).show();

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

##### UrlHolder

This library contains a `ProxerUrlHolder` class which provides methods to obtain
various often needed Urls. One example is the Url of the image of a News:

```java
private String getUrlToNewsImage(News news){
    return ProxerUrlHolder.getNewsImageUrl(news.getId(), news.getImageId());
}
```

##### Cleanup

When you are done using the API, you can use the `cleanup` method of the
`ProxerRequest`. This cancels all ongoing requests and speeds up the garbage
collection.

##### Managing Cookies

The API needs Cookies to recognize the login state. Android doesn't
automatically handle this itself, but this library comes with a helper class for
this purpose.
To enable cookie handling, add the following to your main Applications
`onCreate`:

```java
CookieManager cookieManager = new CookieManager(new PersistentCookieStore(this),
                CookiePolicy.ACCEPT_ALL);
CookieHandler.setDefault(cookieManager);
```

##### More

You can find detailed JavaDoc
![here](https://jitpack.io/com/github/proxer/ProxerLibAndroid/1.9.0/javadoc/).

### Architecture

##### Organisation

The actual REST API is organized in classes. This library tries to mirror those
as closely as possible. For each class there is a package with all requests in
the `connection` package. The NewsRequest for example is in the `notification`
package just as in the REST API.

![This page](https://proxer.me/wiki/Proxer_API/v1) contains more information
about the REST API.

##### Experimental APIs

In some versions there might be an `experimental` package. This package contains
requests or other features which *should* work, but are not documented and
officially supported. Note that those are potentially dangerous as they might
get removed without further notice.

### Dependencies

This library highly relies on [Bridge](https://github.com/afollestad/bridge) by
[Aidan Follestad](https://github.com/afollestad) for the network communication.  
Moreover it uses
[Android Support Annotations](http://tools.android.com/tech-docs/support-annotations)
to improve the code style and provide IDE support.
