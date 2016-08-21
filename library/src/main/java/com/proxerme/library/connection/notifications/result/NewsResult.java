package com.proxerme.library.connection.notifications.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.notifications.entitiy.News;

/**
 * The Result of a {@link com.proxerme.library.connection.notifications.request.NewsRequest}.
 *
 * @author Ruben Gees
 */
public class NewsResult extends ProxerResult<News[]> {

    News[] data;

    NewsResult() {

    }

    @Override
    public News[] getData() {
        return data;
    }
}
