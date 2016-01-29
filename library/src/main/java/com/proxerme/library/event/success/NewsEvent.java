package com.proxerme.library.event.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.News;
import com.proxerme.library.event.IListEvent;

import java.util.List;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsEvent implements IListEvent<News> {

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
