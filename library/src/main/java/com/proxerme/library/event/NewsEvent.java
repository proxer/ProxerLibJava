package com.proxerme.library.event;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.News;

import java.util.List;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsEvent implements IEvent<List<News>> {

    private List<News> item;

    public NewsEvent(@NonNull List<News> item) {
        this.item = item;
    }

    @Override
    @NonNull
    public List<News> getItem() {
        return item;
    }
}
