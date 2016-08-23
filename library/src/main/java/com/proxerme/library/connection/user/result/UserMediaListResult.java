package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.UserMediaListEntry;
import com.squareup.moshi.Json;

public class UserMediaListResult extends ProxerResult<UserMediaListEntry[]> {

    @Json(name = "data")
    private UserMediaListEntry[] entries;

    private UserMediaListResult() {
    }

    @Override
    public UserMediaListEntry[] getData() {
        return entries;
    }
}
