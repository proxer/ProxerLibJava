package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.News;
import com.proxerme.library.result.ProxerListResult;

import java.util.List;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsResult implements ProxerListResult<News> {

    private List<News> item;

    public NewsResult(@NonNull List<News> item) {
        this.item = item;
    }

    @Override
    @NonNull
    public List<News> getItem() {
        return item;
    }
}
