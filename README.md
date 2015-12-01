# ProxerLibAndroid

### What is this?

This is a library, providing some core functionality for an Android App aiming to implement the API of the [Proxer.me](https://proxer.me/) website. 

### Include in your project:

Add this to your build.gradle:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

```groovy
dependencies {
    compile('com.github.proxer:ProxerLibAndroid:1.2.7@aar') {
        transitive = true
    }
}
```

### Usage

The `ProxerConnection` class provides static methods to retrieve Java representations of the different APIs. Each call to one of the retrieval methods will return a subclass of the `ProxerRequest`, which has the methods `execute()` and `executeSynchronized()`. 

#### The `execute()` method

The request will automatically happen on a worker thread, thus not blocking the UI. You will mostly use this. To get the result, you pass a `ConnectionCallback`, whose methods will be called, when the request is done. 

#### The `executeSynchronized()` method

The request will happen on the current thread. This is useful, if you want to use an `IntentService` (which is asynchronous by default) or manage the Threads yourself.

The `ProxerConnection` also provides several other methods:

#### `cancel()` and `cancelSync()`

These methods will cancel all current request of a specified type. E.g. all login-requests. The difference between `cancel()` and `cancelSync()` is the same as with the `execute()` methods.

#### `init()` and `cleanup()`

You *must* call `init()` somewhere in the android lifecycle. The `onCreate()` method of your main activity is recommended for that. If you do not do that, you may get weird Exceptions if a request was unsuccessful.

The `cleanup()` method is optional, but recommended. It cancels all the active requests and frees up the memory for the java garbage collection. A good place to call this is the `onDestroy()` method of your main Activity.

#### Managing Cookies

The Api needs Cookies to recognize the login state. Android doesn't automatically handle this itself, but this Lib has a helper-class for this purpose.
To activate cookie handling, add the following to your main Applications `onCreate`:

```java
CookieManager cookieManager = new CookieManager(new PersistentCookieStore(this),
                CookiePolicy.ACCEPT_ALL);
CookieHandler.setDefault(cookieManager);
```

### Dependencies

This library highly relies on [Bridge](https://github.com/afollestad/bridge) by [Aidan Follestad](https://github.com/afollestad) for the network communication.  
Moreover it uses the [Android Support Annotations](http://tools.android.com/tech-docs/support-annotations) to improve the code style.
