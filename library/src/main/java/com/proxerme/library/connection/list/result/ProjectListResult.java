package com.proxerme.library.connection.list.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.list.entity.ProjectListEntry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class ProjectListResult extends ProxerResult<ProjectListEntry[]> {

    @Json(name = "data")
    private ProjectListEntry[] entries;

    protected ProjectListResult() {
    }

    @Override
    public ProjectListEntry[] getData() {
        return entries;
    }
}