package com.proxerme.library.event.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Message;
import com.proxerme.library.event.IListEvent;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesEvent implements IListEvent<Message> {

    private List<Message> messages;

    public MessagesEvent(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public List<Message> getItem() {
        return messages;
    }
}
