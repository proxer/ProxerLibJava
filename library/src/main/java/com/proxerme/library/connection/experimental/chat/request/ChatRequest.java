package com.proxerme.library.connection.experimental.chat.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.chat.result.ChatResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class ChatRequest extends ProxerRequest<ChatResult> {

    private static final String CHAT_URL = "/messages?format=json&json=messages&id=%s&p=%s";

    private String conferenceId;
    private int page;

    public ChatRequest(@NonNull String conferenceId, @IntRange(from = 0) int page) {
        this.conferenceId = conferenceId;
        this.page = page;
    }

    @Override
    protected ChatResult parse(Response response) throws Exception {
        return response.asClass(ChatResult.class);
    }

    @Override
    protected int getTag() {
        return ProxerTag.CHAT;
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CHAT_URL;
    }

    @Nullable
    @Override
    protected String[] getParameters() {
        return new String[]{conferenceId, String.valueOf(page)};
    }
}
